<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'MyJsp.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<table>
			<thead></thead>
			<tfoot></tfoot>
			<tbody>
				<tr>
					<td>
						定购
					</td>
					<td>
						<a href="/sme/product/inter/testOrderReq.action?from=000001">集中平台触发</a>
						<a href="/sme/product/inter/testOrderReq.action?from=000002">应用平台触发</a>


					</td>
				</tr>

				<tr>
					<td>
						订购确认
					</td>
					<td>


						<a href="/sme/product/inter/testOrderCfmReq.action">RSME->C</a>
					</td>
				</tr>

				<tr>
					<td>
						订购结果通知
					</td>
					<td>
						<a
							href="/sme/product/inter/testOrderResultNotifyReq.action?from=000003">BOSS->SME</a>
						<a
							href="/sme/product/inter/testOrderResultNotifyReq.action?from=000002">C->SME</a>

					</td>
				</tr>
				<tr>
					<td>
						退订
					</td>
					<td>
						<a href="/sme/product/inter/testUnOrderReq.action?from=000001">集中平台触发</a>
						<a href="/sme/product/inter/testUnOrderReq.action?from=000002">应用平台触发</a>


					</td>
				</tr>

				<tr>
					<td>
						退订结果通知
					</td>
					<td>
						<a
							href="/sme/product/inter/testUnOrderResultNotifyReq.action?from=000003">BOSS->SME</a>
						<a
							href="/sme/product/inter/testUnOrderResultNotifyReq.action?from=000002">C->SME</a>

					</td>
				</tr>

				<tr>
					<td>
						退订确认
					</td>
					<td>


						<a href="/sme/product/inter/testUnOrderCfmReq.action">RSME->C</a>
					</td>
				</tr>



				<tr>
					<td>
						订购关系状态变更
					</td>
					<td>


						<a href="/sme/product/inter/testOrdStateNotifyReq.action">测试</a>
					</td>
				</tr>
				<tr>
					<td>
						订购关系查询
					</td>
					<td>


						<a href="/sme/product/inter/testGetOrdInfoReq.action">测试</a>
					</td>
				</tr>
				<tr>
					<td>
						订购关系鉴权
					</td>
					<td>


						<a href="/sme/product/inter/testValidateOrderReq.action">测试</a>
					</td>
				</tr>
				<tr>
					<td>
						订购关系同步
					</td>
					<td>


						<a href="/sme/product/inter/testSynOrderInfoReq.action">测试</a>
					</td>
				</tr>


				<tr>
					<td>
						计费
					</td>
					<td>


						<a href="/sme/product/inter/testFeeByCountReq.action?from=000001">集中平台触发</a>
						<a href="/sme/product/inter/testFeeByCountReq.action?from=000002">应用平台触发</a>

					</td>
				</tr>
				<tr>
					<td>
						资源同步通知
					</td>
					<td>


						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=1&result=0">测试上线通知</a>
						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=3&result=0">测试下线通知</a>
						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=2&result=0">测试预下线通知</a>
						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=1&result=1">测试上线失败通知</a>
						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=3&result=1">测试下线失败通知</a>
						<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=2&result=1">测试预下线失败通知</a>
				<a
							href="/sme/product/inter/testSynResultNotifyReq.action?type=2&result=1">BOSS退订桩</a>
				
					</td>
<!-- 
					<iframe src="http://192.168.1.102:8080/sme/appAccess.action?appid=AP420000000000010000&accesstype=1&urlcategory=1" />
 -->				</tr>
			</tbody>
		</table>
	</body>
</html>
