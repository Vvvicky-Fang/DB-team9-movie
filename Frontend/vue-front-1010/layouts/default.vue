<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="/" title="mymovie">
            <img src="~/assets/img/logo.png" width="100%" alt="mmymovie">
          </a>
        </h1>
        <div class="h-r-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a class="navi">Home</a>
            </router-link>
            <router-link to="/movie" tag="li" active-class="current">
              <a class="navi">Movies</a>
            </router-link>
            <router-link to="/actor" tag="li" active-class="current">
            <a class="navi">Actors</a>
            </router-link>
            <router-link to="/collection" tag="li" active-class="current">
              <a class="navi">My Collections</a>
            </router-link>
          </ul>
          <!-- / nav -->
          <ul class="h-r-login">
            <li v-if="!LoginInfo.userID" id="no-login">
              <a href="/login" title="登录">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">sign in |</span>
              </a>
              <a href="/register" title="注册">
                <span class="vam ml5">sign up</span>
              </a>
            </li>
            <li v-if="LoginInfo.userID" id="is-login-one" class="mr10">
              <a id="headerMsgCountId"  title="消息">
                <em class="icon18 news-icon">&nbsp;</em>
              </a>
              <q class="red-point" style="display:none">&nbsp;</q>
            </li>
            <li v-if="LoginInfo.userID" class="h-r-user" id="is-login-two">
              <a title>
                <img
                  :src="LoginInfo.avatarPath"
                  width="30"
                  height="30"
                  class="vam picImg"
                  alt
                >
                <span class="disIb vam" id="userName">{{ LoginInfo.username }}</span>
              </a>
              <a href="javascript:void(0)" title="exit" @click="exit()" class="ml5 info">| Exit</a>

            </li>
            <!-- /未登录显示第1 li；登录后显示第2，3 li -->
          </ul>
          <aside class="h-r-search">
            <form >
              <label class="h-r-s-box">
                <input type="text" style="width: 160px; font-size: 22px; font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;" v-model="searchName" placeholder="search movie" name="queryCourse.courseName" value>
                
                <button type="submit" @click="handleSearch()" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <!-- <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside> -->
        <div class="clear"></div>
      </section>
    </header>
      


    <!-- /公共头引入 这边存放了pages里面的index -->
    <nuxt/>

    <!-- 公共底引入 -->
    <footer id="footer">
      <section class="container">
        <div class>
          <h4 class="hLh30">
            <span class="fsize18 f-fM c-999" style="font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; font-size: 25px;">Team 9</span>
          </h4>
          <ul class="of flink-list">
            <li>
              <span title="Database System" target="_blank" style="font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; font-size: 18px;">Instructor: Prof. Jia-Ling Koh </span>
            </li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="b-foot">
          <section class="fl col-7">
            <section class="mr20">
              <section class="b-f-link">
                <a href="#" title="关于我们" target="_blank" class="info">About us</a>|
                <a href="#" title="联系我们" target="_blank" class="info">Contact us</a>|
                <a href="#" title="帮助中心" target="_blank" class="info">Help</a>|
                <a href="#" title="资源下载" target="_blank" class="info">Resources</a>|
              </section>
              <section class="b-f-link mt10">
                <span class="info">©1112 Database Theories</span>
              </section>
            </section>
          </section>
     
          <div class="clear"></div>
        </div>
      </section>
    </footer>
    <!-- /公共底引入 -->
  </div>
</template>
<script>
import '~/assets/css/reset.css'
import '~/assets/css/theme.css'
import '~/assets/css/global.css'
import '~/assets/css/web.css'
import '~/assets/css/base.css'
import '~/assets/css/activity_tab.css'
import '~/assets/css/bottom_rec.css'
import '~/assets/css/nice_select.css'
import '~/assets/css/order.css'
import '~/assets/css/swiper-3.3.1.min.css'
import "~/assets/css/pages-weixinpay.css"
import cookie from 'js-cookie'
import movieApi from '@/api/course'


export default {
  data(){
    return{
      token:'',
      LoginInfo:{
        userID:'',
        avatarPath:'',
        email:'',
        username:'',
        dialogFormVisible:false,
        searchName:''
      }
    }
  },
  created(){
    this.showInfo()
  },
  methods:{
    //get userinfo from cookie
    showInfo(){
      var userStr = cookie.get('team9_user')
      if(userStr){
        this.LoginInfo = JSON.parse(userStr)
      }
    },
    exit(){
      console.log("exit excuted")
      cookie.set('team9_token','',{domain:'localhost'})
      cookie.set('team9_user','',{domain:'localhost'})
      //
      window.location.href="/"
    },
    openDialog(){
      console.log("click")
      this.dialogFormVisible = true
    },
    handleSearch(){
      this.getMovieId()
    },
    getMovieId(){
      movieApi.getByMovieName(this.searchName)
        .then(response=>{
          if(response.data.data.movieId == -1){
            //需要chatGPT幫我navigate到/movie頁面
            window.location.href = '/movie'
          }else{
            //需要chatGPT幫我navigate到/movie/'+response.data.data.movieId頁面
            window.location.href = '/movie/' + response.data.data.movieId
          }
        })
    },
  }
};
</script>
<style scoped>
.navi{
  font-size: 22px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.vam{
  font-size: 22px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.info
{
  font-size: 18px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
</style>