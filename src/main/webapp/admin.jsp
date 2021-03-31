<%@ page import="cn.com.yanyuchen.mymusic_web.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iMusic音乐 管理员界面</title>
    <link rel="icon" type="image/png" sizes="128x64" href="static/imgs/logo.png"/>
</head>
<body>
<jsp:include page="top.jsp?page=admin"/>
<link rel="stylesheet" type="text/css" href="static/css/mymusic.css">
<div id="admin_app">
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {%>
    <%--这一小部分其实不会起作用--%>
    <div class="js_mod_profile_unlogin play" style="">
        <div class="mod_profile_unlogin">
            <div class="section_inner">
                <h2 class="profile_unlogin__tit">
                    <div class="icon_txt">听我喜欢听的歌</div>
                </h2>
                <div class="profile_unlogin__desc"></div>
                <a href="javascript:" data-stat="y_new.profile.login"
                   class="mod_btn_green profile_unlogin__btn js_login"
                   @click="top_app.loginDialogVisible = true">立即登录</a>
            </div>
        </div>
    </div>
    <%} else {%>
    <%--背景板 呈现当前用户信息--%>
    <div class="mod_profile js_user_data">
        <div class="section_inner">
            <div class="profile__cover_link">
                <img src="<%=user.getAvatar()%>"
                     onerror="this.src='//y.gtimg.cn/mediastyle/global/img/person_300.png?max_age=31536000';this.onerror=null;"
                     alt="<%=user.getUserName()%>" class="profile__cover" id="profileHead">
            </div>
            <h1 class="profile__tit">
                <span class="profile__name"><%=user.getUserName()%></span>
            </h1>
        </div>
    </div>

    <%--用户信息表  可以用来修改--%>
    <div style="height: auto; min-height: 60%;text-align: center; margin-top: 30px; padding-top:40px; padding-left: 40px;padding-right: 40px;margin-bottom: 60px;">
        <el-table :data="userList" stripe style="width: 45%; margin: auto;" class="customer-no-border-table"
                  v-loading="loading">

            <el-table-column prop="userName" label="用户名" min-width="30%" header-align="center">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.userName" type="text"></el-input>
                </template>
            </el-table-column>
            <el-table-column prop="gender" label="性别" min-width="10%" header-align="center">
                <template slot-scope="scope">
                    <el-select v-model="scope.row.gender">
                        <el-option value="1" label="男"></el-option>
                        <el-option value="0" label="女"></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column prop="email" label="邮箱" min-width="30%" header-align="center">
                <template slot-scope="scope">
                    <el-input v-model="scope.row.email" type="text"></el-input>
                </template>
            </el-table-column>
            <el-table-column prop="auth" label="权限" min-width="20%" header-align="center">
                <template slot-scope="scope">
                    <el-select v-model="scope.row.auth">
                        <el-option label="管理员" value="1"></el-option>
                        <el-option label="普通权限" value="0"></el-option>
                    </el-select>
                </template>
            </el-table-column>
            <el-table-column label="操作" min-width="10%" header-align="center">
                <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="updateUser(scope.row)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>

    <%}%></div>
<jsp:include page="bottom.jsp"/>

<script>
    let vm = new Vue({
        el: "#admin_app",
        data() {
            return {
                loading: false,
                userList: [{
                    userName: '',
                    gender: '',
                    email: '',
                    auth: '',
                }]
            }
        },
        mounted() {
            this.getUsers();
        },
        methods: {
            getUsers() {
                this.loading = true
                axios.get("api/users").then((res) => {
                    this.userList = res.data
                    this.loading = false;
                }, (error) => {
                    if (error.response.status === 403) {
                    } else {
                        this.$message.error('查询失败，请联系管理员！')
                    }
                    this.loading = false;
                })
            },
            updateUser(row) {
                this.loading = true
                axios.post("api/updateUser", {
                    "id": row.id,
                    "userName": row.userName,
                    "email": row.email,
                    "gender": row.gender,
                    "auth": row.auth,
                }).then((res) => {
                    this.$message.success('修改成功')
                    this.loading = false;
                }, (error) => {
                    this.$message.error('修改失败，请联系管理员！')
                    this.loading = false;
                })
            }
        },


    })

</script>
</body>

</html>
