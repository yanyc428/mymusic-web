<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>iMusic 歌曲</title>
    <link rel="icon" type="image/png" sizes="128x64" href="static/imgs/logo.png"/>
</head>
<body>
<jsp:include page="top.jsp?page=songs"/>
<link rel="stylesheet" type="text/css" href="static/css/songs.css"/>

<%
    String word = request.getParameter("word");
    String curPage = request.getParameter("page");
    if (word == null) {
        word = "";
    }
    if (curPage == null) {
        curPage = "1";
    }
%>


<div id="songs_app">
    <%--搜索--%>
    <div class="mod_search" style="background-image:url(//y.gtimg.cn/mediastyle/yqq/img/bg_search.jpg);" role="search">
        <div class="mod_search_input">
            <input v-model="word" class="search_input__input" aria-label="请输入搜索内容" type="text" placeholder="请输入搜索内容"
                   @keyup.enter="getSongs">
            <button @click="getSongs" class="search_input__btn"><i class="icon_search sprite"></i><span
                    class="icon_txt">搜索</span>
            </button>
        </div>
    </div>

    <%--呈现--%>
    <div style="height: auto; min-height: 60%;text-align: center; margin-top: 30px; padding-top:40px; padding-left: 40px;padding-right: 40px;margin-bottom: 60px;">
        <el-table :data="songList" stripe style="width: 45%; margin: auto;" class="customer-no-border-table"
                  v-loading="loading" empty-text="空空如也，赶快查询你喜爱的歌曲吧！">
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
            <el-table-column prop="name" label="歌手" min-width="20%">
                <template slot-scope="scope">
                    <a :href="scope.row.singer.url" target="_blank">{{scope.row.singer.name}}</a>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="专辑" min-width="24%">
                <template slot-scope="scope">
                    <a :href="scope.row.albumUrl" target="_blank">{{scope.row.album}}</a>
                </template>
            </el-table-column>
        </el-table>
    </div>

    <%--分页--%>
    <div class="mod_page_nav" v-if="totalPage>1">
        <el-pagination
                style="text-align: center;"
                :current-page.sync="currentPage"
                :page-count="totalPage"
                layout="prev, pager, next"
                background @current-change="change">
        </el-pagination>
    </div>
</div>
</div>


<jsp:include page="bottom.jsp"/>

<script>
    let vm = new Vue({
        el: "#songs_app",
        data() {
            return {
                loading: false,
                word: "<%=word%>",
                songList: [],
                totalPage: 1,
                currentPage: <%=curPage%>,
            }
        },
        mounted() {
            if (this.word !== "") {
                this.getSongs()
            }
        },
        methods: {
            getSongs() {
                let stateObj = {}
                let title = "new_url"
                let newUrl = "songs.jsp?word=" + this.word + "&page=" + this.currentPage
                history.pushState(stateObj, title, newUrl)
                this.loading = true
                axios.post("api/songs", {"word": this.word, "page": this.currentPage}).then((res) => {
                    this.songList = res.data.data
                    this.totalPage = res.data.totalPage
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
            },
            change() {
                this.getSongs();
            }
        }
    })

</script>
</body>
</html>
