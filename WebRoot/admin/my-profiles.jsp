<%@page import="me.imomo.typeasy.vo.User"%>
<%@page import="me.imomo.typeasy.commons.Gravatar"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setAttribute("title", "个人资料");
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<section id="main" class="column">
  <h4 class="alert_info">在这里你可以查看和修改你的个人信息。</h4>
  <article class="module width_full">
    <header>
      <h3 class="tabs_involved">我的资料</h3>
      <ul class="tabs">
        <li><a href="#tab1">查看信息</a></li>
        <li><a href="#tab2">修改信息</a></li>
      </ul>
    </header>
    <div class="tab_container">
      <div id="tab1" class="tab_content">
        <div class="my-profiles">
          <h1 style="text-align:center;">查看个人信息</h1>
          <hr>
          <h4>用户头像</h4>
          <p><%=Gravatar.getAvatar(((User) session.getAttribute("user"))
					.getMail())%></p>
          <h4>用户UID</h4>
          <p>${sessionScope.user.getUid() }</p>
          <h4>用户名</h4>
          <p>${sessionScope.user.getName() }</p>
          <h4>昵称</h4>
          <p>${sessionScope.user.getScreenName() }</p>
          <h4>电子邮箱</h4>
          <p>${sessionScope.user.getMail() }</p>
          <h4>个人主页</h4>
          <p>${sessionScope.user.getUrl() }</p>
          <h4>注册时间</h4>
          <p>${sessionScope.user.getCreated() }</p>
          <h4>用户权限</h4>
          <p>${sessionScope.user.getGroup() }</p>
        </div>
      </div>
      <!-- end of #tab1 -->
      
      <div id="tab2" class="tab_content">
        <div class="my-profiles">
        <form id="my-profile" action="#" method="post">
          <h1 style="text-align:center;">修改个人信息</h1>
          <hr>
          <h2>个人资料</h2>
          <table class="form-table" align="center">
            <tr>
              <th><label for="user_name">用户名 <span class="description">(用户名不可更改)</span></label></th>
              <td><input type="text" name="user_name" id="user_name" value="${sessionScope.user.getName() }" class="regular-text" disabled="disabled"/></td>
            </tr>
            <tr>
              <th><label for="user_screenName">昵称</label></th>
              <td><input type="text" name="user_screenName" id="user_screenName" value="${sessionScope.user.getScreenName() }" class="regular-text code" /></td>
            </tr>
          </table>
          <br>
          <br>
          <h2>联系信息</h2>
          <table class="form-table" align="center">
            <tr>
              <th><label for="user_mail">电子邮箱 <span class="description">(必填)</span></label></th>
              <td><input type="text" name="user_mail" id="user_mail" value="${sessionScope.user.getMail() }" class="regular-text" /></td>
            </tr>
            <tr>
              <th><label for="user_url">个人主页</label></th>
              <td><input type="text" name="user_url" id="user_url" value="http://imomo.me" class="regular-text code" /></td>
            </tr>
          </table>
          <br>
          <br>
          <p align="right"><input name="submit" type="submit" value="更新个人信息" /></p>
          </form>
        </div>
      </div>
      <!-- end of #tab2 --> 
      
    </div>
    <!-- end of .tab_container --> 
    
  </article>
  <!-- end of content manager article -->
  
  <div class="clear"></div>
  <div class="spacer"></div>
</section>
<jsp:include page="footer.jsp"></jsp:include>
