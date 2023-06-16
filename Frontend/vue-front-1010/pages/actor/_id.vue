<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- Actor  -->
    <section class="container">
      <div class="movie-container">
        <div class="movie-header" :style="{backgroundImage: 'linear-gradient(to bottom, rgba(255,255,255,0), rgba(255,255,255,1)), url(' + baseUrl +actor.imagePath+ ')'}">
          <div class="left-section">
            <img :src="baseUrl+actor.imagePath" :alt="actor.name" class="movie-poster">
          </div>
          <div class="movie-info">
            <h2 class="movie-title">{{actor.name}}</h2>
            <p class="movie-description" style="margin-top: 40px;">NATIONALITY: {{actor.nationality}}</p>
            <p class="movie-description">BIOGRAPHY: {{actor.biography}}</p>
          </div>
        </div>
      </div>
      <!-- <div class="t-infor-wrap">
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="baseUrl+actor.imagePath">
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">{{ actor.name }}</span>
            </h3>
            <section class="mt10">
              <span class="t-tag-bg">{{ actor.nationality }}</span>
            </section>
            <section class="t-infor-txt">
              <p
                class="mt20"
              >{{ teacher.notableWorks }}</p>
            </section>
            <div class="clear"></div>
          </div>
        </section>
        <div class="clear"></div>
      </div> -->
      <section class="mt30" style=" margin-top: 50px;">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac" >
              <span class="c-333 repre">Representative works</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /无数据提示 开始-->
          <section v-if="movieList.length==0" class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize16 ml10 vam">No data found...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article v-if="movieList.length>0" class="comm-course-list">
            <ul class="of">
              <li v-for="movie in movieList" :key="movie.movieID">
                <div class="cc-l-wrap">
                    <section class="course-img">
                      <img :src="baseUrl+movie.picUrl" class="img-responsive" :alt="movie.title">
                      <div class="cc-mask">
                        <a :href="'/movie/'+movie.movieID" title="watch" class="comm-btn c-btn-1">watch</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="'/movie/'+movie.movieID" :title="movie.title" class="movieTitleFont fsize20 c-333">{{ movie.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <!-- <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{ Number(item.price) === 0 ?"免費":"付費" }}</i>
                      </span> -->
                    </section>
                  </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
      </section>
    </section>
    <!-- /讲师介绍 结束 -->
  </div>
</template>
<script>
import teacherApi from '@/api/teacher'
export default {
  data(){
    return{
      teacher:{},
      actor:{},
      movieList:{},
      courseList:{},
      baseUrl:'https://image.tmdb.org/t/p/original/',
    }
  },
  created(){
    this.getActorInfo()
  },
  methods:{
    getActorInfo(){
      teacherApi.getActorInfo(this.$route.params.id)
        .then(response =>{
          console.log(this.data)
          this.actor = response.data.data.actor
          this.movieList = response.data.data.actor.movieList
        })
    }
  },
};
</script>
<style scoped>
.movie-container {
  color: black;
  font-family: Arial, sans-serif;
}

.movie-header {
  display: flex;
  background: #fff;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  padding: 20px;
}

.movie-poster {
  margin-top: 300px;
  border-radius: 12px;
  width: 300px;
  height: 450px;
  object-fit: cover;
}

.movie-info {
  margin-top: 300px;
  width: 70%;
  padding-left: 20px;
}

.movie-title {
  margin-top: 20px;
  font-size: 70px;
  line-height: 0.9;
  font-weight: bold;
  color: #000;
  margin-bottom: 10px;
  margin-right: 38px;
  word-break: keep-all;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.movie-description {
  margin: 20px 0;
  font-size: 20px;
  line-height: 1.6;
  color: #000;
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin-right: 38px;
  word-break: keep-all;

}
.left-section {
  display: flex;
  flex-direction: column;
  margin-left: 38px;
  align-items: flex-start; 
}

.cast {
  font-size: 23px;
  font-weight: bold;
  text-align: left;
  margin-top: 80px;
  margin-left: 3px;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.actor-list {
  display: flex;
  overflow-x: scroll;
}

.actor-card {
  width: 120px;
  text-align: center;
  margin-right: 25px;
}

.actor-img {
  margin-top: 20px;
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 12px;

}

.actor-name {
  font-size: 16.5px;
  margin-top: 4px;
  line-height: 1.6;
  color: #000;
  word-break: keep-all;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.movie-info {
  margin-left: 20px; /* 设定与movie-poster的距离 */
}

.repre{
  font-size: 30px;
  font-weight: bold;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}
.movieTitleFont{
  font-size: 22px;
  font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

</style>
