<template>
    <div id="aCoursesList" class="bg-fa of">
      <!-- /movie list 开始 -->
      <section class="container">
        <header class="comm-title">
          <h2 class="fl tac">
            <span class="c-333 custom-font">My Collectons</span>
          </h2>
        </header>
        <section class="c-sort-box">
          <div class="js-wrap">
          </div>
          <div class="mt40">
            <!-- /无数据提示 开始-->
            <section class="no-data-wrap" v-if="movieList.length === 0">
              <em class="icon30 no-data-ico">&nbsp;</em>
              <span class="c-666 fsize16 ml10 vam">No data...</span>
            </section>
            <!-- /无数据提示 结束-->
            <article class="comm-course-list" v-if="movieList.length > 0">
              <ul class="of" id="bna">
                <li v-for="item in movieList" :key="item.movieID">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img :src="baseUrl+item.picUrl" class="img-responsive" :alt="item.title">
                      <div class="cc-mask">
                        <a :href="'/movie/'+item.movieID" title="watch" class="comm-btn c-btn-1">watch</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="'/movie/'+item.movieID" :title="item.title" class="movieTitleFont fsize20 c-333">{{ item.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <!-- <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{ Number(item.price) === 0 ?"免費":"付費" }}</i>
                      </span> -->
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA" style="font-size: 15px;">Official Rate: {{ item.rateAvg }}</i>
                        |
                        <i class="c-999 f-fA" style="font-size: 15px;"> {{ item.releaseDate | formatDate }}</i>
                      </span>
                    </section>
                  </div>
                </li>
                
              </ul>
              <div class="clear"></div>
            </article>
          </div>
        </section>
      </section>
      <!-- /课程列表 结束 -->
    </div>
  </template>
  <script>
  import courseApi from '@/api/course'
  import cookie from 'js-cookie'
  export default {
    data(){
      return{
        baseUrl:'https://image.tmdb.org/t/p/original/',
        movieList:[],
        LoginInfo:{
          userID:'',
          avatarPath:'',
          email:'',
          username:'',
        }
      }
    },
    filters: {
      formatDate(value) {
        if (!value) return ''
        value = value.toString()
        return value.split(' ')[0]
      }
    },
    created(){
      this.showInfo()
    },
    mounted(){
      this.getMovieList()
    },
    methods:{
      showInfo(){
        console.log("get cookie:")
        var userStr = cookie.get('team9_user')
        if(userStr){
          this.LoginInfo = JSON.parse(userStr)
        }
      },
      getMovieList(){
        if(this.LoginInfo.userID == ''){
          this.$message({
                        type:'false',
                        message:'Please Login'
                    })
        }else{
          courseApi.getMyCollections(this.LoginInfo.userID)
          .then(response=>{
             this.movieList= response.data.data.movieList
          })
        }
      },
    },
  };
  </script>
<style scoped>
  .active{
    background: #C0F3DC;
  }
  .hide{
    display: none;
  }
  .show{
    display: block;
  }

  .custom-font {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 26px; /* 修改为您想要的字体大小 */
    font-weight: bold; /* 修改为您想要的字体粗细 */
  
  }

  .movieTitleFont{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; 
  font-size: 22px;
}
</style>