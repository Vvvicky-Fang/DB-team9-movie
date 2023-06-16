<template>
    <div class="main">
      <div class="title">
        <a class="active" href="/login">sign in</a>
        <span>·</span>
        <a href="/register">sign up</a>
      </div>
  
      
      <div class="sign-up-container">
        <client-only>

        <el-form ref="userForm" :model="user">
  
          <el-form-item class="input-prepend restyle" prop="email" :rules="[{ required: true, message: 'please enter your email', trigger: 'blur' },{validator: checkEmail, trigger: 'blur'}]">
            <div >
              <el-input type="text" placeholder="Your email account" v-model="user.email"/>
              <i class=" iconfont iconfont2" />
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: 'please enter passowrd', trigger: 'blur' }]">
            <div>
              <el-input type="password" placeholder="Password" v-model="user.password"/>
              <i class="iconfont icon-password"/>
            </div>
          </el-form-item>
          <div class="btn">
            <input type="button" class="sign-in-button" value="Login" @click="submitLogin()">
          </div>
        </el-form>
        </client-only>



      </div>
  
    </div>
  </template>
  
  <script>
    import '~/assets/css/sign.css'
    import '~/assets/css/iconfont.css'
    import { Message } from 'element-ui';
  
    import cookie from 'js-cookie'

    import loginApi from '@/api/login'
    export default {
      layout: 'sign',
  
      data () {
        return {
          user:{
            email:'',
            password:''
          },
          //用户信息
          loginInfo:{},
          token:''
        }
      },
  
      methods: {
        submitLogin(){
           //Step1: 调用接口，返回token字符串
            loginApi.submitLogin(this.user)
              .then(response =>{
                if(response.success == false){
                  console.log("失败了")
                  this.$message({
                      type: 'success',
                      message: "登陆失败"
                    })
                }else{
                  //Step2: 获取token 放入cookie中
                  this.token = response.data.data.token
                  cookie.set('team9_token',response.data.data.token,{domain:'localhost'})
                  //Step3:創建攔截器
                  //已經在request.js中實現
                  //Step4: 根據token 獲取用戶信息，為了首頁顯示
                  loginApi.getUserInfo()
                    .then(response =>{
                      this.loginInfo = JSON.stringify(response.data.data.userInfo)
                      console.log("!!info:"+JSON.stringify(this.loginInfo))
                      cookie.set('team9_user', this.loginInfo,{domain:'localhost'})
                      
                      //跳轉登陸頁面
                      window.location.href = "/"
                    })
                  }
              })
        },
        checkEmail(rule, value, callback) {
          if (!(/^[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}$/).test(value)) {
            return callback(new Error('Email账号格式不正确'));
          }
          return callback();
        }

      }
    }
  </script>
  <style> 
     .el-form-item__error{
      z-index: 9999999;
    }
    i.iconfont2 {
        margin-top: 8px;
        margin-right: 10px;
        margin-left: -4px;
        display: inline-block;
        width: 24px; /* Set the width of your icon */
        height: 24px; /* Set the height of your icon */
        background-image: url('assets/mail.svg');
        background-repeat: no-repeat;
        background-size: contain;
    }
  </style>