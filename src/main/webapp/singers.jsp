<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <title>iMusic 歌手</title>

    <link rel="icon" type="image/png" sizes="128x64" href="static/imgs/logo.png"/>
</head>
<body>
<jsp:include page="top.jsp?page=singers"/>
<link rel="stylesheet" type="text/css" href="static/css/singers.css">

<div class="mod_singer_push js_my_singers_nologin">
    <div class="section_inner">
        <h2 class="singer_push__tit"><i class="icon_txt">万千歌手，尽在眼前</i></h2>
    </div>
</div>


<div class="main"
     style="min-height:50%;height: auto; margin-top: 30px; padding-top:40px; padding-left: 40px;padding-right: 40px;"
     id="singers_app">
    <%--搜索--%>
    <el-form>
        <el-form-item>
            <el-radio-group v-model="firstLetter" size="mini" @change="changeLetter">
                <el-radio-button label="A"></el-radio-button>
                <el-radio-button label="B"></el-radio-button>
                <el-radio-button label="C"></el-radio-button>
                <el-radio-button label="D"></el-radio-button>
                <el-radio-button label="E"></el-radio-button>
                <el-radio-button label="F"></el-radio-button>
                <el-radio-button label="G"></el-radio-button>
                <el-radio-button label="H"></el-radio-button>
                <el-radio-button label="I"></el-radio-button>
                <el-radio-button label="J"></el-radio-button>
                <el-radio-button label="K"></el-radio-button>
                <el-radio-button label="L"></el-radio-button>
                <el-radio-button label="M"></el-radio-button>
                <el-radio-button label="N"></el-radio-button>
                <el-radio-button label="O"></el-radio-button>
                <el-radio-button label="P"></el-radio-button>
                <el-radio-button label="Q"></el-radio-button>
                <el-radio-button label="R"></el-radio-button>
                <el-radio-button label="S"></el-radio-button>
                <el-radio-button label="T"></el-radio-button>
                <el-radio-button label="U"></el-radio-button>
                <el-radio-button label="V"></el-radio-button>
                <el-radio-button label="W"></el-radio-button>
                <el-radio-button label="X"></el-radio-button>
                <el-radio-button label="Y"></el-radio-button>
                <el-radio-button label="Z"></el-radio-button>
            </el-radio-group>
        </el-form-item>
        <el-form-item>
            <el-radio-group v-model="gender" size="mini" @change="changeGender">
                <el-radio-button label="3"
                                 v-if="(this.platform === '2') && (this.musicType==='5')">其他
                </el-radio-button>
                <div v-else>
                    <el-radio-button label="1">男</el-radio-button>
                    <el-radio-button label="2">女</el-radio-button>
                    <el-radio-button label="3">组合</el-radio-button>
                </div>


            </el-radio-group>
        </el-form-item>
        <el-form-item>
            <el-radio-group v-model="musicType" size="mini" @change="changeType">
                <el-radio-button label="1">华语</el-radio-button>
                <el-radio-button label="2">欧美</el-radio-button>
                <el-radio-button label="3" v-if="this.platform !== '2'">日语</el-radio-button>
                <el-radio-button label="4" v-if="this.platform !== '2'">韩语</el-radio-button>
                <el-radio-button label="5">其他</el-radio-button>
            </el-radio-group>
        </el-form-item>
        <el-form-item>
            <el-radio-group v-model="platform" size="mini" @change="changePlatform">
                <el-radio-button label="0">QQ音乐</el-radio-button>
                <el-radio-button label="1">网易云音乐</el-radio-button>
                <el-radio-button label="2">酷狗音乐</el-radio-button>
            </el-radio-group>
        </el-form-item>
    </el-form>
    <%--结果呈现--%>
    <div style="min-height: 30%;" v-loading="loading">
        <ul class="singer_list_txt">
            <li class="singer_list_txt__item" v-for="(singer, i) in singerList">
                <a :href="singer.url" target="_blank" class="singer_list_txt__link js_singer" :title="singer.name">{{singer.name}}</a>
            </li>
        </ul>
    </div>
    <%--分页--%>
    <div class="mod_page_nav" v-if="totalPage>1">
        <el-pagination
                style="text-align: center"
                :current-page.sync="currentPage"
                :page-count="totalPage"
                layout="prev, pager, next"
                background @current-change="getSingers">
        </el-pagination>
    </div>

</div>


<jsp:include page="bottom.jsp"/>
</body>

<script>
    let vm = new Vue({
        el: "#singers_app",
        data() {
            return {
                loading: false,
                firstLetter: "A",
                gender: "1",
                musicType: "1",
                platform: "0",
                singerList: [{name: "歌手1", url: "#"}, {name: "歌手2", url: "#"}, {name: "歌手3", url: "#"},
                    {name: "歌手4", url: "#"}, {name: "歌手1", url: "#"}, {name: "歌手2", url: "#"},
                    {name: "歌手3", url: "#"}, {name: "歌手4", url: "#"}],
                totalPage: 1,
                currentPage: 1,
            }
        },
        mounted() {
            this.getSingers()
        }
        ,
        methods: {
            changeGender: function (val) {
                this.getSingers()
            },
            changeType: function (val) {
                if ((this.platform === '2') && (val === '5')) {
                    this.gender = "3"
                }
                // else{
                //     if(this.gender === "3"){
                //         this.gender = "1"
                //     }
                // }
                this.getSingers()
            },
            changeLetter(val) {
                this.getSingers()
            },
            changePlatform(val) {
                if (val === "2") {
                    if (this.musicType === "4") {
                        this.musicType = "3"
                    }
                }
                // else{
                //     if(this.musicType === "4" ){
                //         this.musicType = "1"
                //     }
                //     if(this.musicType === "5"){
                //         this.gender = "1"
                //     }
                // }
                this.getSingers()
            },
            getSingers() {
                this.loading = true
                axios.post("api/singers", {
                    "gender": this.gender, "firstLetter": this.firstLetter
                    , "type": this.musicType, "platform": this.platform, "page": this.currentPage
                }).then((res) => {
                    console.log(res.data)
                    this.singerList = res.data.data
                    this.totalPage = res.data.totalPage
                    console.log(this.singerList)
                    this.loading = false;
                }, (error) => {
                    this.$message.error('查询失败，请联系管理员！')
                    this.loading = false;
                })
            }
        }
    })

</script>
</html>
