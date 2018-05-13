<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div class="pager" style="display:none;">
        <table style="margin-top:4px;" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td align="left" nowrap="nowrap">
                    <span class="txt">
                        <span style="display:none;">
                            显示 <span class="firstrcd">1</span> 到 <span class="endrcd">1</span>，
                        </span>
                        共 <span class="totalrcd">1</span> 条记录
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </span>
                </td>
                <td nowrap="nowrap"><span class="txt">每页&nbsp;&nbsp;</span></td>
                <td nowrap="nowrap">
                    <span class="clr">
                        <select class="pagesize" style="width:50px">
                            <option>5</option>
                            <option>10</option>
                            <option>15</option>
                            <option>20</option>
                            <option>30</option>
                            <option>40</option>
                            <option>50</option>
                        </select>
                    </span>
                </td>
                <td nowrap="nowrap"><span class="txt">&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                <td nowrap="nowrap">
                    <span class="clr"><a href="javascript:void(0)" class="bluebtn firstpage"><span class="itembody">首页</span></a></span>
                </td>
                <td nowrap="nowrap">
                    <span class="clr"><a href="javascript:void(0)" class="bluebtn prevpage"><span class="itembody">上一页</span></a></span>
                </td>
                <td nowrap="nowrap">
                    <span class="clr"><a href="javascript:void(0)" class="bluebtn nextpage"><span class="itembody">下一页</span></a></span>
                </td>
                <td nowrap="nowrap">
                    <span class="clr"><a href="javascript:void(0)" class="bluebtn endpage"><span class="itembody">末页</span></a></span>
                </td>
                <td nowrap="nowrap">
                    <span class="clr"><input name="textfield" type="hidden" class="input currentpagenum" id="textfield" style="width:40px;padding-bottom: 4px;"/></span>
                </td>
            </tr>
        </table>
    </div>
