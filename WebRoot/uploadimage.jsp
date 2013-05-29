<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String picUrl = request.getParameter("Picurl");
	String step = request.getParameter("step");
	String defaultPic = "images/default.jpg";
	String avatar = request.getParameter("avatar");
	if (avatar != null && !("").equals(avatar))
		defaultPic = avatar;
	if ("3".equals(step))
		defaultPic = "uploads/User/UserHeadImage/" + picUrl;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>上传头像</title>
<link href="css/upload.css" type="text/css" rel="Stylesheet" />
<script type="text/javascript" src="js/jquery1.2.6.pack.js"></script>
<script type="text/javascript" src="js/ui.core.packed.js"></script>
<script type="text/javascript" src="js/ui.draggable.packed.js"></script>
<script type="text/javascript" src="js/CutPic.js"></script>
<script type="text/javascript">
	function Step1() {
		$("#Step2Container").hide();
	}

	function Step2() {
		$("#CurruntPicContainer").hide();
	}
	function Step3() {
		$("#Step2Container").hide();
	}
</script>
</head>
<body>


	<div>
		<div class="left">
			<!--当前照片-->
			<div id="CurruntPicContainer">
				<div class="title">
					<b>当前照片</b>
				</div>
				<div class="photocontainer">
					<img id="imgphoto" src="<%=defaultPic%>" style="border-width:0px;"
						width="120" height="120" />
				</div>
				<%
					if ("3".equals(step)) {
						session.setAttribute("newAvatar", defaultPic);
				%>
				<input type="button" name="submitPic" value="完成"
					onclick="opener.location=opener.location;window.close();" />
				<%
					}
				%>
			</div>
			<!--Step 2-->
			<div id="Step2Container">
				<div class="title">
					<b> 裁切头像照片</b>
				</div>
				<div class="uploadtooltip">您可以拖动照片以裁剪满意的头像</div>
				<div id="Canvas" class="uploaddiv">

					<div id="ImageDragContainer">
						<img id="ImageDrag" class="imagePhoto"
							src="uploads/UploadPhoto/<%=picUrl%>" style="border-width:0px;" />
					</div>
					<div id="IconContainer">
						<img id="ImageIcon" class="imagePhoto"
							src="uploads/UploadPhoto/<%=picUrl%>" style="border-width:0px;" />
					</div>
				</div>
				<div class="uploaddiv">
					<table>
						<tr>
							<td id="Min"><img alt="缩小" src="images/_c.gif"
								onmouseover="this.src='images/_c.gif';"
								onmouseout="this.src='images/_h.gif';" id="moresmall"
								class="smallbig" /></td>
							<td>
								<div id="bar">
									<div class="child"></div>
								</div>
							</td>
							<td id="Max"><img alt="放大" src="images/c.gif"
								onmouseover="this.src='images/c.gif';"
								onmouseout="this.src='images/h.gif';" id="morebig"
								class="smallbig" /></td>
						</tr>
					</table>
				</div>
				<form action="servlet/ZoomImage" method="post">
					<input type="hidden" name="picture" value="<%=picUrl%>" />
					<div class="uploaddiv">
						<input type="submit" name="btn_Image" value="保存头像" id="btn_Image" />
					</div>
					<div>
						<input name="txt_width" type="hidden" value="1" id="txt_width" /><br />
						<input name="txt_height" type="hidden" value="1" id="txt_height" /><br />
						<input name="txt_top" type="hidden" value="82" id="txt_top" /><br />
						<input name="txt_left" type="hidden" value="73" id="txt_left" /><br />
						<input name="txt_DropWidth" type="hidden" value="120"
							id="txt_DropWidth" /><br /> <input name="txt_DropHeight"
							type="hidden" value="120" id="txt_DropHeight" /><br /> <input
							name="txt_Zoom" type="hidden" id="txt_Zoom" />
					</div>
				</form>
			</div>

		</div>
		<form name="form1" method="post" action="servlet/UpLoadUserHeadImage"
			id="form1" enctype="multipart/form-data">
			<div class="right">
				<!--Step 1-->
				<div id="Step1Container">
					<div class="title">
						<b>更换照片</b>
					</div>
					<div id="uploadcontainer">
						<div class="uploadtooltip">请选择新的照片文件，文件需小于2.5MB</div>
						<div class="uploaddiv">
							<input type="file" name="fuPhoto" id="fuPhoto" title="选择照片" />
						</div>
						<div class="uploaddiv">
							<input type="submit" name="btnUpload" value="上 传" id="btnUpload" />
						</div>
					</div>

				</div>
			</div>
		</form>
	</div>
	<%
		if (null == picUrl || "".equals(picUrl)) {
	%>
	<script type='text/javascript'>
		Step1();
	</script>
	<%
		} else if (!"".equals(picUrl) && "2".equals(step)) {
	%>
	<script type='text/javascript'>
		Step2();
	</script>
	<%
		} else if (!"".equals(picUrl) && "3".equals(step)) {
	%>
	<script type='text/javascript'>
		Step3();
	</script>
	<%
		}
	%>

</body>
</html>