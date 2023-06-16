<template>
    <div class="main">
      <div class="title">
        <a href="/login">sign in</a>
        <span>·</span>
        <a class="active" href="/register">sign up</a>
      </div>
  
      <div class="sign-up-container">
        <client-only>
                  <el-form ref="userForm" :model="params">
  
          <el-form-item class="input-prepend restyle" prop="username" :rules="[{ required: true, message: 'please enter name', trigger: 'blur' }]">
            <div>
              <el-input type="text" placeholder="Your nickname" v-model="params.username"/>
              <i class="iconfont icon-user"/>
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend restyle" prop="email" :rules="[{ required: true, message: 'please enter your email', trigger: 'blur' },{validator: checkEmail, trigger: 'blur'}]">
            <div >
              <el-input type="text" placeholder="Your email account" v-model="params.email"/>
              <i class=" iconfont iconfont2" />
            </div>
          </el-form-item>
  
          <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: 'please enter passowrd', trigger: 'blur' }]">
            <div>
              <el-input type="password" placeholder="Setting Password" v-model="params.password"/>
              <i class="iconfont icon-password"/>
            </div>
          </el-form-item>
  
          <div class="btn">
            <input type="button" class="sign-up-button" value="sign up" @click="submitRegister()">
          </div>
          <!-- <p class="sign-up-msg">
            点击 “注册” 即表示您同意并愿意遵守简书
            <br>
            <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
            和
            <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
          </p> -->
        </el-form>
        </client-only>

      </div>
    </div>
  </template>
  
  <script>
    import '~/assets/css/sign.css'
    import '~/assets/css/iconfont.css'
  
    import register from '@/api/register'

    export default {
      layout: 'sign',
      data() {
        return {
          params: { //封装注册输入数据
            email: '',
            // code: '',  //验证码
            username: '',
            password: ''
          },
          sending: true,      //是否发送验证码
          second: 60,        //倒计时间
          codeTest: '获取验证码'
        }
      },
      methods: {
        submitRegister(){
            register.registerUser(this.params)
                .then(response =>{
                  if(response.success == false){
                    this.$message({
                        type:'false',
                        message:response.message
                    })
                  }else{
                    this.$message({
                        type:'success',
                        message:'Register Success'
                    })
                    this.$router.push({path:'/login'})
                  }
                })
        },

        checkEmail(rule, value, callback) {
          if (!(/^[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}$/).test(value)) {
            return callback(new Error('Email account format is incorrect'));
          }
          return callback();
        }

        
      }
    }
  </script>
  <style scoped>
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
  