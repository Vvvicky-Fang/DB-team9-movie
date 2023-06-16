<template>
  <div>
    <!-- Homepage -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">
        <a v-for="banner in bannerList" :key="banner.movieID" :href="'/movie/' + banner.movieID" class="swiper-slide" :style="{backgroundImage: 'linear-gradient(to right, rgba(255,255,255,1), rgba(255,255,255,0), rgba(255,255,255,1)), url(' + baseUrl + banner.bannerPath + ')', backgroundPosition: 'center', backgroundSize: 'cover', backgroundRepeat: 'no-repeat'}">
          <div class="slide-content">
            <!-- <img :src="baseUrl+banner.picUrl" :alt="banner.title" class="movie-poster"> -->
            <!-- <h2 class="movie-title">{{banner.title}}</h2> -->
          </div>
        </a>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
    </div>
    <!-- 幻灯片 结束 -->
    
     <div id="aCoursesList">
      <!-- 熱門電影 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac" style="margin-top: 50px;">
              <span class="c-333 mylablefont">Latest Movies</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="movie in movieList" :key="movie.movieID">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <a :href="/movie/+movie.movieID">
                        <img
                        :src="baseUrl+movie.picUrl"
                        class="img-responsive"
                        :alt="movie.title"
                        >
                      </a>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="/movie/+movie.movieID" :title="movie.title" class="course-title fsize20 c-333 movieTitleFont" >{{movie.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <!-- <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634人学习</i>
                        |
                        <i class="c-999 f-fA">9634评论</i>
                      </span> -->
                    </section>
                  </div>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20" style="margin-top:-50px;">
              <a href="/movie" title="全部课程" class="comm-btn c-btn-2 myfont" >View All Movies</a>
            </section>
          </div>
        </section>
      </div>
      <!-- 熱門電影 结束 -->
      <!-- Stars 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac" style="margin-top: 60px;">
              <span class="c-333 mylablefont">Popular Actors</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for=" actor in actorList" :key="actor.actorID" class="flex-item">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <a :href="'/actor/'+actor.actorID" :title="actor.name" >
                        <img class="rounded-image" :src="baseUrl+actor.imagePath" :alt="actor.name">
                      </a>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <a :href="'/actor/'+actor.actorID" :title="actor.name"  class="fsize18 c-666 movieTitleFont" >{{actor.name}}</a>
                    </div>
                    <!-- <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999">{{actor.notableWorks}}</span>
                    </div> -->
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/actor" title="全部讲师" class="comm-btn c-btn-2 myfont">View All Actors</a>
            </section>
          </div>
        </section>
      </div>
      <!-- Stars 结束 -->
    </div>
  </div>
</template>

<script>
import index from '@/api/index'
import movieApi from '@/api/course'
import actorApi from '@/api/teacher'
export default {
  data () {
    return {
      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      },
      bannerList:[],
      eduList:[],
      teacherList:[],

      movieList:[],
      actorList:[],
      bannerNum:3,
      baseUrl:'https://image.tmdb.org/t/p/original/'
    }
  },
  created(){
    this.getBannerList()
    this.getTopMovieAndActor()
  },
  methods:{
    getTopMovieAndActor(){
      this.getTopMovie()
      this.getTopActor()
    },
    getTopMovie(){
      movieApi.getNewMovies(4)
        .then(response =>{
          this.movieList = response.data.data.movieList 
        })
    },
    getTopActor(){
      actorApi.getTopActor(4)
        .then(response =>{
          this.actorList = response.data.data.actorList 
        })
    },
    getBannerList(){
      movieApi.getNewMovies(this.bannerNum)
        .then(response =>{
          this.bannerList = response.data.data.movieList 
        })
    }
  }
}
</script>
<style scoped>
.swiper-slide {
  position: relative;
  width: 100%;
  height: 100%;
}

.slide-content {
  position: absolute;
  top: 50%;
  left: 10%;
  transform: translateY(-50%);
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
}

.movie-poster {
  border-radius: 12px;
  width: 300px;
  height: 450px;
  object-fit: cover;
  margin-right: 50px;
}

.movie-title {
  font-size: 70px;
  line-height: 0.9;
  font-weight: bold;
  color: #000;
  margin-bottom: 10px;
  word-break: keep-all;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
.myfont{

  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  margin-bottom: 30px;
}

.mylablefont{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size: 40px;
  color: #000;
  position: relative; /* 添加相对定位 */
  z-index: 1; /* 设置较高的 z-index 值 */
}

.movieTitleFont{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; 
  font-size: 22px;
}
/* .rounded-image {
  border-radius: 20%;
} */

</style>