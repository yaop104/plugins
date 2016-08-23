package com.sme.util;


import com.sme.entity.AppNode;
import com.sme.entity.PAppDetail;
import com.sme.service.checkApk.ICheck;
import com.sme.service.checkApk.imp.ModifyCheck;
import com.sme.service.checkApk.imp.NewApplicationCheck;
import com.sme.service.checkApk.imp.NewDetailCheck;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * @包名：com.cmcc.pluginportal.util.file.proxy
 * @类名：ApkCheckProxy
 * @描述：(校验插件是否符合要求)
 * @作者：hejh
 * @时间：2015-1-19下午3:16:12
 * @版本：1.0.0
 */
public class ApkCheckProxy implements IFileSaver {
	
	
	private PAppDetail detail;
	
	private IFileSaver local;
	
	private JSONObject result = null;

    private HttpServletRequest request;
	
	@Override
	public JSONObject getResult() {
		if(null != result) {
			return result;
		}
		else {
			return local.getResult();
		}

	}

	@Override
	public void save(MultipartFile file, String name) {

		local.save(file, name);

		try {
			if(local.getResult().getCode()!=500) {
				return;
			}
		}
		catch(Exception e) {


			return;
		}

//		String path = request.getServletContext().getRealPath("apk") + "/" + name;
		String path = Config.DEFAULT_APK_PATH + File.separator  + name;
		JSONObject jsonObject = new JSONObject();
		AppNode appNode = new AppNode();
		if(!SignApk.checkDigests(path)) {

			jsonObject.setCode(501);
			jsonObject.setInfo("上传组件文件不完整，请检查文件是否正确后重新上传");
			this.result = jsonObject;
			return;
		}

//		ApkinformatonUtil util = new ApkinformatonUtil(request);
		ApkinformatonUtil util = new ApkinformatonUtil();

		try {
			util.processApk(path);

			if(!util.checkActionAndPackage(path)) {
				jsonObject.setCode(501);
				jsonObject.setInfo("上传组件的AndroidManifest校验失败，请检查文件是否符合规则后重新上传");
				this.result = jsonObject;
				return;
			}

			ICheck check = new NewApplicationCheck();
			ICheck check2 = new NewDetailCheck();
			ICheck check3 = new ModifyCheck();
			check.setNext(check2);
			check2.setNext(check3);

			appNode.setApp_name(util.getAppname());
			appNode.setApp_apk(name);
			appNode.setApp_size(file.getSize() / 1024);
			appNode.setApp_version(util.getVersionCode());
			appNode.setApp_versionname(util.getVersionName());
			appNode.setApp_package_name(util.getPackagename());
			appNode.setApp_action_name(util.getActionname());
			appNode.setApp_cert_digest(util.getDigest());
			appNode.setApp_logo(util.getIconpath());
			appNode.setApp_md5(MD5Util.getFileMD5String(path));
			appNode.setApp_platform_version(util.getOsversion());

			jsonObject.setAppNode(appNode);

			check.check(jsonObject, util, detail);

			this.result = jsonObject;
		}
		catch(Exception e) {
			// TODO Auto-generated catch block

			jsonObject.setCode(501);
			jsonObject.setInfo("上传失败！");
			this.result = jsonObject;
		}

	}

	public ApkCheckProxy(IFileSaver out, PAppDetail detail, HttpServletRequest request) {
		this.local = out;
		this.detail = detail;
        this.request = request;
	}
}
