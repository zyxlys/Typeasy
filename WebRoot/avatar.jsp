<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<base href="<%=basePath%>">
<title>上传头像</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
	String pic = ((String) session.getAttribute("picName")) + ".png";
	String pic1 = ((String) session.getAttribute("pic1Name")) + ".png";
	String pic2 = ((String) session.getAttribute("pic2Name")) + ".png";
	String pic3 = ((String) session.getAttribute("pic3Name")) + ".png";
	session.removeAttribute("picName");
	session.removeAttribute("pic1Name");
	session.removeAttribute("pic2Name");
	session.removeAttribute("pic3Name");
	String step = request.getParameter("step");
	String avatar = request.getParameter("avatar");
%>

<script type="text/javascript">
	function uploadevent(status) {
		status += '';
		switch (status) {
		case '1':
			window.location.href = "avatar.jsp?step=2";
			break;
		break;
	case '-1':
		window.location.reload();
		break;
	default:
		window.location.reload();
	}
}
</script>
</head>
<body>
	<%
		if (!(("2").equals(step))) {
	%>
	<div id="altContent">


		<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
			WIDTH="650" HEIGHT="450" id="avatar">
			<PARAM NAME=movie VALUE="swf/avatar.swf">
			<PARAM NAME=quality VALUE=high>
			<PARAM NAME=bgcolor VALUE=#FFFFFF>
			<param name="flashvars"
				value="imgUrl=<%=avatar%>&uploadUrl=servlet/AvatarServlet&uploadSrc=false" />
			<EMBED src="swf/avatar.swf" quality=high bgcolor=#FFFFFF WIDTH="650"
				HEIGHT="450" wmode="transparent"
				flashVars="imgUrl=<%=avatar%>&uploadUrl=servlet/AvatarServlet&uploadSrc=false"
				NAME="avatar" ALIGN="center" TYPE="application/x-shockwave-flash"
				allowScriptAccess="always"
				PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer">
			</EMBED>
		</OBJECT>


	</div>
	<%
		} else {
			session.setAttribute("newAvatar", "uploads/avatar/" + pic1);
	%>
	<div id="avatar_priview">
		头像1 : <img src="uploads/avatar/<%=pic1%>" alt="头像1" /> <br /> 头像2 :
		<img src="uploads/avatar/<%=pic2%>" alt="头像2" /> <br /> 头像3 : <img
			src="uploads/avatar/<%=pic3%>" alt="头像3" /> <br /> <br /> <br />
		<p align="center">
			<input type="button" name="submitPic" value="保存头像"
				onclick="opener.location=opener.location;window.close();" />
		</p>
	</div>
	<%
		}
	%>
</body>
</html>