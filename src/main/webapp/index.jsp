<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html>
<html>

<head>
    <title>iMusic音乐 热爱你的音乐</title>
    <link rel="icon" type="image/png" sizes="128x64" href="static/imgs/logo.png"/>
</head>

<body style="background-color:#F7F7F7;">


<jsp:include page="top.jsp?page=index"/>
<link rel="stylesheet" type="text/css" href="static/css/index.css"/>
<%
    Calendar calendar = Calendar.getInstance();
    int curHour = calendar.get(Calendar.HOUR_OF_DAY);
    String mention = null;
    if ((curHour < 6) || (curHour > 22)) {
        mention = "夜深了";
    } else if (curHour < 8) {
        mention = "清晨";
    } else if (curHour < 12) {
        mention = "上午好";
    } else if (curHour < 13) {
        mention = "中午好";
    } else if (curHour < 17) {
        mention = "午后";
    } else if (curHour < 19) {
        mention = "傍晚";
    } else {
        mention = "晚上好";
    }


%>
<div id="index_app">

    <%--背景板--%>
    <div class="mod_profile js_user_data">
        <div class="section_inner">
            <h1 class="profile__tit">
                <span class="profile__name"><%=mention%>，为你推荐</span>
            </h1>
        </div>
    </div>

    <div style="text-align: center;margin-top: 20px; margin-left: 40%;">
        <el-button icon="el-icon-refresh" style="border: none; background-color:rgba(255,255,255,0);" @click="getSongs">
            换一批
        </el-button>
    </div>
    <%--内容呈现--%>
    <div style="height: auto; min-height: 650px;text-align: center; margin-top: 10px; padding-top:20px; padding-left: 40px;padding-right: 40px;">
        <el-table :data="songList" stripe style="width: 45%; margin: auto;" class="customer-no-border-table"
                  v-loading="loading" element-loading-text="努力为您推荐中">
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
</div>
<%@include file="bottom.jsp" %>

<script>
    let vm = new Vue({
        el: "#index_app",
        data() {
            return {
                loading: false,
                songList: [],
            }
        },
        mounted() {
            this.getSongs();
        },
        methods: {
            getSongs() {
                this.loading = true
                axios.get("api/random").then((res) => {
                    this.songList = res.data
                    this.loading = false;
                }, (error) => {
                    this.$message.error('查询失败，请联系管理员！')
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
        }
    })

</script>
</body>

</html>