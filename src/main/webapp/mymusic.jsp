<%@ page import="cn.com.yanyuchen.mymusic_web.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iMusic 我的音乐</title>
    <link rel="icon" type="image/png" sizes="128x64" href="static/imgs/logo.png"/>
</head>
<body>
<jsp:include page="top.jsp?page=mymusic"/>
<link rel="stylesheet" type="text/css" href="static/css/mymusic.css">
<div id="mymusic_app">
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {%>
    <%--未登录--%>
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
    <%--登录--%>
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

    <%--呈现--%>
    <div style="height: auto; min-height: 60%;text-align: center; margin-top: 30px; padding-top:40px; padding-left: 40px;padding-right: 40px;margin-bottom: 60px;">
        <el-table :data="songList" stripe style="width: 45%; margin: auto;" class="customer-no-border-table"
                  v-loading="loading" empty-text="空空如也，快添加你喜欢的歌曲吧！">
            <el-table-column min-width="6%">
                <template slot-scope="scope">
                    <button class="focus:outline-none like liked" v-if="scope.row.like" @click="unlike(scope.row)">
                        <span class="like-icon like-icon-state" aria-label="state" x-text="state"
                              aria-live="polite">Unliked</span>
                    </button>
                    <button class="focus:outline-none like unliked" v-else @click="like(scope.row)">
                    <span class="like-icon like-icon-state" aria-label="state" x-text="state"
                          aria-live="polite">Unliked</span>
                    </button>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="歌曲" min-width="50%">
                <template slot-scope="scope">
                    <a :href="scope.row.url" target="_blank">{{scope.row.name}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="singer.name" label="歌手" min-width="20%">
                <template slot-scope="scope">
                    <a :href="scope.row.singer.url" target="_blank">{{scope.row.singer.name}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="album" label="专辑" min-width="24%">
                <template slot-scope="scope">
                    <a :href="scope.row.albumUrl" target="_blank">{{scope.row.album}}</a>
                </template>
            </el-table-column>
        </el-table>
    </div>

    <%--分页--%>
    <div class="mod_page_nav" v-if="totalPage>1">
        <el-pagination
                style="text-align: center"
                :current-page.sync="currentPage"
                :page-count="totalPage"
                layout="prev, pager, next"
                background @current-change="getLoved">
        </el-pagination>
    </div>


    <%}%></div>
<jsp:include page="bottom.jsp"/>

<script>
    let vm = new Vue({
        el: "#mymusic_app",
        data() {
            return {
                loading: false,
                songList: [{
                    like: false,
                    name: "歌曲名",
                    url: "#",
                    album: "专辑",
                    albumUrl: "#",
                    singer: {name: "歌手", url: "#"}
                }],
                totalPage: 1,
                currentPage: 1,
            }
        },
        mounted() {
            this.getLoved();
        },
        methods: {
            getLoved() {
                this.loading = true
                axios.post("api/loved", {"page": this.currentPage}).then((res) => {
                    this.songList = res.data.data
                    this.totalPage = res.data.totalPage
                    this.loading = false;
                }, (error) => {
                    if (error.response.status === 403) {
                    } else {
                        this.$message.error('查询失败，请联系管理员！')
                    }
                    this.loading = false;
                })
            },
            like(row) {
                this.loading = true
                axios.post("api/like", {"songId": row.id}).then((res) => {
                    row.like = true
                    this.loading = false;
                }, (error) => {
                    if (error.response.status === 403) {
                        top_app.loginDialogVisible = true
                        this.$message.error('请先登录！')
                    } else {
                        this.$message.error('喜欢失败，请联系管理员！')
                    }
                    this.loading = false;
                })
            },
            unlike(row) {
                this.loading = true
                axios.post("api/unlike", {"songId": row.id}).then((res) => {
                    row.like = false
                    this.loading = false;
                }, (error) => {
                    if (error.response.status === 403) {
                        top_app.loginDialogVisible = true
                        this.$message.error('请先登录！')
                    } else {
                        this.$message.error('取消喜欢失败，请联系管理员！')
                    }
                    this.loading = false;
                })
            }
        },


    })

</script>
</body>
</html>
