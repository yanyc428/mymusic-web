<%@ page import="cn.com.yanyuchen.mymusic_web.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--头部--%>
<html>
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script type="text/javascript" src="static/js/md5.js"></script>

    <link rel="stylesheet" type="text/css" href="static/css/top.css">
</head>
<body>
<% String pageFrom = request.getParameter("page"); %>
<script type="text/javascript">
    if ("Promise" in window) {
    } else {
        alert("请勿使用IE等不支持ES6版本的浏览器！");
        window.location.href = "error-ie.jsp";
    }
</script>
<div id="top_app">
    <div class="mod_header">
        <div class="section_inner">
            <h1 class="qqmusic_title"><a href="index.jsp"><img src="static/imgs/logo.png" alt="QQ音乐"
                                                               class="qqmusic_logo"/></a></h1>
            <!-- 导航 S -->
            <ul class="mod_top_nav" role="nav">
                <% if (pageFrom.equals("index")) {%>
                <li class="top_nav__item">
                    <a href="index.jsp" class="top_nav__link top_nav__link--current" title="音乐馆">音乐馆</a>
                </li>
                <li class="top_nav__item">
                    <a href="mymusic.jsp" class="top_nav__link" title="我的音乐">我的音乐</a>
                </li>
                <li class="top_nav__item">
                    <a href="singers.jsp" class="top_nav__link" title="歌手">歌&nbsp;&nbsp;手</a>
                </li>
                <li class="top_nav__item">
                    <a href="songs.jsp" class="top_nav__link" title="歌曲">歌&nbsp;&nbsp;曲</a>
                </li>
                <%} else if (pageFrom.equals("mymusic")) {%>
                <li class="top_nav__item">
                    <a href="index.jsp" class="top_nav__link" title="音乐馆">音乐馆</a>
                </li>
                <li class="top_nav__item">
                    <a href="mymusic.jsp" class="top_nav__link top_nav__link--current" title="我的音乐">我的音乐</a>
                </li>
                <li class="top_nav__item">
                    <a href="singers.jsp" class="top_nav__link" title="歌手">歌&nbsp;&nbsp;手</a>
                </li>
                <li class="top_nav__item">
                    <a href="songs.jsp" class="top_nav__link" title="歌曲">歌&nbsp;&nbsp;曲</a>
                </li>
                <%} else if (pageFrom.equals("singers")) {%>
                <li class="top_nav__item">
                    <a href="index.jsp" class="top_nav__link" title="音乐馆">音乐馆</a>
                </li>
                <li class="top_nav__item">
                    <a href="mymusic.jsp" class="top_nav__link" title="我的音乐">我的音乐</a>
                </li>
                <li class="top_nav__item">
                    <a href="singers.jsp" class="top_nav__link top_nav__link--current" title="歌手">歌&nbsp;&nbsp;手</a>
                </li>
                <li class="top_nav__item">
                    <a href="songs.jsp" class="top_nav__link" title="歌曲">歌&nbsp;&nbsp;曲</a>
                </li>
                <%} else if (pageFrom.equals("songs")) {%>
                <li class="top_nav__item">
                    <a href="index.jsp" class="top_nav__link" title="音乐馆">音乐馆</a>
                </li>
                <li class="top_nav__item">
                    <a href="mymusic.jsp" class="top_nav__link" title="我的音乐">我的音乐</a>
                </li>
                <li class="top_nav__item">
                    <a href="singers.jsp" class="top_nav__link" title="歌手">歌&nbsp;&nbsp;手</a>
                </li>
                <li class="top_nav__item">
                    <a href="songs.jsp" class="top_nav__link top_nav__link--current" title="歌曲">歌&nbsp;&nbsp;曲</a>
                </li>
                <%} else if (pageFrom.equals("admin")) {%>
                <li class="top_nav__item">
                    <a href="index.jsp" class="top_nav__link" title="音乐馆">音乐馆</a>
                </li>
                <li class="top_nav__item">
                    <a href="mymusic.jsp" class="top_nav__link" title="我的音乐">我的音乐</a>
                </li>
                <li class="top_nav__item">
                    <a href="singers.jsp" class="top_nav__link" title="歌手">歌&nbsp;&nbsp;手</a>
                </li>
                <li class="top_nav__item">
                    <a href="songs.jsp" class="top_nav__link" title="歌曲">歌&nbsp;&nbsp;曲</a>
                </li>
                <li class="top_nav__item">
                    <a href="admin.jsp" class="top_nav__link top_nav__link--current" title="管理">管理</a>
                </li>
                <%}%>

                <%
                    User user = (User) session.getAttribute("user");
                    if (user == null) {
                %>
                <li class="top_nav__item top_nav__item--subnav">
                    <a href="https://pan.bnu.edu.cn/l/xncuqn" class="top_nav__link js_toDownload" title="客户端"
                       rel="noopener" target="_blank">客户端</a>
                    <img src="http://y.gtimg.cn/mediastyle/yqq/extra/mark_1.png" class="top_nav__mark">
                </li>


                <%} else if (user.isAuth() && !pageFrom.equals("admin")) {%>
                <li class="top_nav__item">
                    <a href="admin.jsp" class="top_nav__link" title="管理">管  理</a>
                </li>
                <%} else if (!user.isAuth()) {%>
                <li class="top_nav__item top_nav__item--subnav">
                    <a href="https://pan.bnu.edu.cn/l/xncuqn" class="top_nav__link js_toDownload" title="客户端"
                       rel="noopener" target="_blank">客户端</a>
                    <img src="http://y.gtimg.cn/mediastyle/yqq/extra/mark_1.png" class="top_nav__mark">
                </li>
                <%}%>

            </ul>
            <!-- 导航 E -->
            <div class="mod_top_search" role="search">
                <div class="mod_search_input">
                    <input v-model="word" class="search_input__input" aria-label="请输入搜索内容" type="text"
                           placeholder="搜索音乐、MV、歌单、用户"
                           accesskey="s" @keyup.enter="jump"/>
                    <button @click="jump" class="search_input__btn"><i class="icon_search sprite"></i><span
                            class="icon_txt">搜索</span>
                    </button>
                </div>
            </div>
            <div class="header__opt">


                <!-- 未登录 -->
                <span class="mod_top_login">
                <%

                    if (user == null) {
                %>

					<div class="mod_select mod_select_green mod_select--open js_cover_focus--open">
                	    <a @click="loginDialogVisible = true" class="select__choose">登&nbsp;&nbsp;录</a>
                	</div>

                    <div class="mod_select mod_select--pay js_cover_focus--pay">
                	    <a @click="registerDialogVisible = true" class="select__choose">注&nbsp;&nbsp;册</a>
                	</div>
                <%} else {%>
                    <a class="top_login__link js_logined" @click="profileDialogVisible = true"
                       style="height: 90px;display: flex;align-items: center;">
                        <img src="<%=user.getAvatar()%>"
                             onerror="this.src='//y.gtimg.cn/mediastyle/global/img/person_300.png?max_age=2592000';this.onerror=null;"
                             class="top_login__cover js_user_img">
                    </a>
                    <div class="mod_select mod_select--pay js_cover_focus--pay">
                	    <a class="select__choose" @click="logout">退出登录</a>
                	</div>
                <%}%>

                </span>
                <!-- 用户信息 -->
                <div class="popup_user">
                </div>
            </div>
        </div>
    </div>

    <el-dialog title="登录" :visible.sync="loginDialogVisible" width="40%">
        <el-form
                ref="loginFormRef"
                :model="loginForm"
                :rules="loginFormRules"
                label-width="100px"
                class="login_form"
                v-loading="loading"
        >
            <el-form-item label="用户名" prop="userName" style="width: 70%;">
                <el-input placeholder="请输入您的用户名" v-model="loginForm.userName"
                          prefix-icon="el-icon-user" :suffix-icon="suffixicon"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" style="width: 70%;">
                <el-input placeholder="请输入您的密码" v-model="loginForm.password" type="password" prefix-icon="el-icon-lock"
                          @keyup.enter.native="login('loginFormRef')"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button type="info" @click="resetDialogVisible=true">找回密码</el-button>
            <el-button type="primary" @click="login('loginFormRef')">立即登录</el-button>
        </span>
    </el-dialog>

    <el-dialog title="找回密码" :visible.sync="resetDialogVisible" width="50%">
        <el-form
                ref="resetFormRef"
                :model="resetForm"
                :rules="resetFormRules"
                label-width="100px"
                class="reset_form"
                v-loading="loading"
        >
            <el-form-item label="用户名" prop="userName" style="width: 70%;">
                <el-input placeholder="请输入您的用户名" v-model="resetForm.userName"
                          prefix-icon="el-icon-user" :suffix-icon="suffixicon"></el-input>
            </el-form-item>

            <el-form-item label="验证码" prop="verCode" style="width: 70%;">
                <el-input v-model="resetForm.verCode" style="width: 50%;" placeholder="输入验证码"
                          prefix-icon="el-icon-key"></el-input>
                <el-button type="primary" style="margin-left: 30px;"
                           @click="getResetVerCode" :disabled="verCodeDisable">获取验证码
                </el-button>
            </el-form-item>

            <el-form-item label="密码" prop="password1" style="width: 70%;">
                <el-input placeholder="请输入您的密码" v-model="resetForm.password1" type="password"
                          prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password2" style="width: 70%;">
                <el-input placeholder="请输入您的密码" v-model="resetForm.password2" type="password"
                          prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="reset('resetFormRef')">立即找回</el-button>
        </span>
    </el-dialog>

    <el-dialog title="注册" :visible.sync="registerDialogVisible" width="50%">
        <el-form
                ref="registerFormRef"
                :model="registerForm"
                :rules="registerFormRules"
                label-width="100px"
                class="register_form"
                v-loading="loading"
        >
            <el-form-item label="用户名" prop="userName" style="width: 70%;">
                <el-input placeholder="请输入您的用户名" v-model="registerForm.userName"
                          prefix-icon="el-icon-user" :suffix-icon="suffixicon"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="registerForm.gender" style="height:40px;display: flex; align-items: center;">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="密码" prop="password1" style="width: 70%;">
                <el-input placeholder="请输入您的密码" v-model="registerForm.password1" type="password"
                          prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password2" style="width: 70%;">
                <el-input placeholder="请确认您的密码" v-model="registerForm.password2" type="password"
                          prefix-icon="el-icon-lock"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" style="width: 70%;">
                <el-input placeholder="请输入您的邮箱" v-model="registerForm.email" type="text"
                          prefix-icon="el-icon-message"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verCode" style="width: 70%;">
                <el-input v-model="registerForm.verCode" style="width: 50%;" placeholder="输入验证码"
                          prefix-icon="el-icon-key"></el-input>
                <el-button type="primary" style="margin-left: 30px"
                           @click="getRegisterVerCode" :disabled="verCodeDisable">获取验证码
                </el-button>
            </el-form-item>
            <el-form-item label="头像">
                <el-upload
                        class="avatar-uploader"
                        action="api/upload"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"
                        accept=".png,.jpg,.jpeg,.JPG,.JPEG">
                    <img v-if="registerForm.avatar" :src="registerForm.avatar" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="register('registerFormRef')">立即注册</el-button>
        </span>
    </el-dialog>

    <el-dialog title="我的信息" :visible.sync="profileDialogVisible" width="50%">
        <el-form
                ref="profileFormRef"
                :model="profileForm"
                :rules="profileFormRules"
                label-width="100px"
                class="profile_form"
                v-loading="loading"
        >
            <el-form-item label="用户名" prop="userName" style="width: 70%;">
                <el-input placeholder="请输入您的用户名" v-model="profileForm.userName"
                          prefix-icon="el-icon-user" :suffix-icon="suffixicon"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="profileForm.gender" style="height:40px;display: flex; align-items: center;">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="邮箱" prop="email" style="width: 70%;">
                <el-input placeholder="请输入您的用户名" v-model="profileForm.email"
                          prefix-icon="el-icon-message" disabled></el-input>
            </el-form-item>
            <el-form-item label="头像">
                <el-upload
                        class="avatar-uploader"
                        action="api/upload"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccessProfile"
                        :before-upload="beforeAvatarUpload"
                        accept=".png,.jpg,.jpeg,.JPG,.JPEG">
                    <img v-if="profileForm.avatar" :src="profileForm.avatar" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="profile('profileFormRef')">修改信息</el-button>
        </span>
    </el-dialog>


</div>

<script>
    let top_app = new Vue({
        el: "#top_app",
        data() {
            let checkRegisterPassword = (rule, value, callback) => {
                if (value !== this.registerForm.password1) {
                    return callback(new Error('输入密码不一致'))
                } else {
                    callback()
                }
            };
            let checkResetPassword = (rule, value, callback) => {
                if (value !== this.resetForm.password1) {
                    return callback(new Error('输入密码不一致'))
                } else {
                    callback()
                }
            };
            let checkVerCode = (rule, value, callback) => {
                if (value === '') {
                    return callback(new Error('验证码不能为空'))
                } else if (value.toLocaleLowerCase() === this.verCode.toLocaleLowerCase()) {
                    return callback()
                } else {
                    return callback(new Error('验证码错误'))
                }
            };
            let checkUserNotExist = (rule, value, callback) => {
                this.suffixicon = "el-icon-loading"
                axios.post("api/checkExist", {"userName": value}).then((res) => {
                    this.suffixicon = "el-icon-check"
                    return callback()
                }, (error) => {
                    this.suffixicon = "el-icon-close"
                    return callback(new Error('用户名已经被使用'))
                })
            };
            let checkUserExist = (rule, value, callback) => {
                this.suffixicon = "el-icon-loading"
                axios.post("api/checkExist", {"userName": value}).then((res) => {
                    this.suffixicon = "el-icon-close"
                    return callback(new Error('不存在的用户名'))
                }, (error) => {
                    this.suffixicon = "el-icon-check"
                    return callback()
                })
            };
            return {
                suffixicon: "",
                word: "",
                verCode: 'changePasswordAdmin',
                verCodeDisable: false,
                loading: false,
                loginDialogVisible: false,
                registerDialogVisible: false,
                resetDialogVisible: false,
                profileDialogVisible: false,
                loginForm: {
                    userName: '',
                    password: ''
                },
                resetForm: {
                    userName: '',
                    verCode: '',
                    password1: '',
                    password2: ''
                },
                registerForm: {
                    userName: '',
                    password1: '',
                    password2: '',
                    email: '',
                    gender: '',
                    avatar: null,
                    verCode: '',
                },

                profileForm: {
                    id: <%=user!=null?user.getId():0%>,
                    userName: '<%=user!=null?user.getUserName():""%>',
                    email: '<%=user!=null?user.getEmail():""%>',
                    gender: '<%=user!=null?(user.isGender()?"1":"0"):""%>',
                    avatar: '<%=user!=null?user.getAvatar():""%>',
                },
                loginFormRules: {
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'},
                        {validator: checkUserExist, trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入用户密码', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'}
                    ]
                },
                registerFormRules: {
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'},
                        {validator: checkUserNotExist, trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '请设置性别', trigger: 'blur'},
                    ],
                    password1: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'}
                    ],
                    password2: [
                        {required: true, message: '请确认密码', trigger: 'blur'},
                        {validator: checkRegisterPassword, trigger: 'change'}
                    ],
                    email: [
                        {required: true, message: '邮箱不能为空', trigger: 'blur'},
                        {
                            pattern: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
                            message: '请输入正确的邮箱格式',
                            trigger: 'change'
                        }
                    ],
                    verCode: [
                        {required: true, message: '请输入验证码', trigger: 'blur'},
                        {validator: checkVerCode, trigger: 'change'}
                    ]
                },
                resetFormRules: {
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'},
                        {validator: checkUserExist, trigger: 'blur'}
                    ],
                    password1: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'}
                    ],
                    password2: [
                        {required: true, message: '请确认密码', trigger: 'blur'},
                        {validator: checkResetPassword, trigger: 'change'}
                    ],
                    verCode: [
                        {required: true, message: '请输入验证码', trigger: 'blur'},
                        {validator: checkVerCode, trigger: 'change'}
                    ]
                },
                profileFormRules: {
                    userName: [
                        {required: true, message: '请输入用户名', trigger: 'blur'},
                        {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'change'},
                        {validator: checkUserNotExist, trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '请设置性别', trigger: 'blur'},
                    ]
                },

            }
        },
        methods: {
            login(formName) {
                // 表单预验证
                // valid：bool类型
                this.$refs[formName].validate(async valid => {
                    if (!valid) return false
                    this.loading = true
                    // 返回值为promise，可加await简化操作 相应的也要加async
                    axios.post("api/login", {
                        "userName": this.loginForm.userName,
                        "password": hex_md5(this.loginForm.password)
                    }).then((res) => {
                        this.$message.success('登录成功')
                        location.reload();
                    }, (error) => {
                        if (error.response.status === 404) {
                            this.loginForm.password = ''
                            this.$message.error('密码错误')
                            this.loading = false;
                        } else {
                            this.$message.error('登录失败，请联系管理员！')
                            this.loading = false;
                        }
                    })
                })
            },
            reset(formName) {
                this.$refs[formName].validate(async valid => {
                    if (!valid) return false
                    this.loading = true
                    // 返回值为promise，可加await简化操作 相应的也要加async
                    axios.post("api/reset", {
                        "userName": this.resetForm.userName,
                        "password": hex_md5(this.resetForm.password1),
                        "verCode": this.registerForm.verCode
                    }).then((res) => {
                        this.$message.success('修改成功')
                        this.loading = false
                        this.resetDialogVisible = false
                    }, (error) => {
                        this.$message.error('修改失败，请联系管理员！')
                        this.loading = false;
                    })
                })
            },
            register(formName) {
                this.$refs[formName].validate(async valid => {
                    if (!valid) return false
                    this.loading = true
                    // 返回值为promise，可加await简化操作 相应的也要加async
                    axios.post("api/register", {
                        "userName": this.registerForm.userName,
                        "password": hex_md5(this.registerForm.password1),
                        "avatar": this.registerForm.avatar,
                        "email": this.registerForm.email,
                        "gender": this.registerForm.gender,
                        "verCode": this.registerForm.verCode
                    }).then((res) => {
                        this.$message.success('注册成功')
                        this.loading = false
                        window.location.reload()
                    }, (error) => {
                        this.$message.error('注册失败，请联系管理员！')
                        this.loading = false;
                    })
                })
            },
            getRegisterVerCode() {
                this.verCodeDisable = true
                axios.post("api/verCode", {"type": "register", "userName": this.registerForm.email}).then((res) => {
                    this.$message.success('验证码已发送！')
                    this.verCode = res.data
                }, (error) => {
                    if (error.response.status === 502) {
                        this.$message.error('请检查您的邮箱！')
                    } else {
                        this.$message.error('验证码获取失败！')
                    }
                    this.verCodeDisable = false
                })

            },
            getResetVerCode() {
                this.verCodeDisable = true
                axios.post("api/verCode", {"type": "reset", "userName": this.resetForm.userName}).then((res) => {
                    this.$message.success('验证码已发送！')
                    this.verCode = res.data
                }, (error) => {
                    if (error.response.status === 404) {
                        this.$message.error('此用户未注册！')
                    } else {
                        this.$message.error('验证码获取失败！')
                    }
                    this.verCodeDisable = false
                })

            },
            jump() {
                window.location.href = "songs.jsp?word=" + this.word + "&page=1"
            },
            logout() {
                axios.get("api/logout",).then((res) => {
                    this.$message.success('注销成功')
                    location.reload();
                }, (error) => {
                    this.$message.error('注销失败，请联系管理员！')
                })
            },
            handleAvatarSuccess(res, file) {
                this.registerForm.avatar = res;
            },
            handleAvatarSuccessProfile(res, file) {
                this.profileForm.avatar = res;
            },
            beforeAvatarUpload(file) {
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isLt2M;
            },
            profile(formName) {
                this.$refs[formName].validate(async valid => {
                    if (!valid) return false
                    this.loading = true
                    // 返回值为promise，可加await简化操作 相应的也要加async
                    axios.post("api/updateUser", {
                        "id": this.profileForm.id,
                        "userName": this.profileForm.userName,
                        "avatar": this.profileForm.avatar,
                        "gender": this.profileForm.gender
                    }).then((res) => {
                        this.$message.success('修改成功')
                        this.loading = false
                        window.location.reload()
                    }, (error) => {
                        this.$message.error('修改失败，请联系管理员！')
                        this.loading = false;
                    })
                })
            }
        }
    })
</script>
</body>
</html>
