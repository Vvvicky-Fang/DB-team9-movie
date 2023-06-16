<template>
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <div class="movie-container">
        <div class="movie-header" :style="{backgroundImage: 'linear-gradient(to bottom, rgba(255,255,255,0), rgba(255,255,255,1)), url(' + baseUrl + movieWebVo.bannerPath + ')'}">
         
          <el-row>
            <el-col :span="4">
              <img :src="baseUrl+movieWebVo.picUrl" :alt="movieWebVo.title" class="movie-poster">
            </el-col>
            <el-col :span="10">
              <div class="movie-info">
                <h2 class="movie-title">{{movieWebVo.title}}</h2>
                <div class="movie-genre">
                  <span v-for="genre in movieWebVo.genreList" :key="genre.genreID" class="genre-tag">{{genre.genreName}}</span>
                </div>
                <p class="movie-description">{{movieWebVo.overview}}</p>
                
                <div class="collect-button">
                  <i class="collect-icon" v-bind:class="isCollect ? 'el-icon-star-on importicon':'el-icon-star-off'" @click.stop="changeStart"></i>
                </div>
              </div>
            </el-col>
          </el-row>

          <el-row>
            <h3 class="cast">Cast</h3>
            <div class="actor-list">
              <div class="actor-card" v-for="actor in movieWebVo.actorList" :key="actor.id">
                <a :href="'/actor/'+actor.actorID">
                  <img :src="baseUrl+actor.imagePath" class="actor-img">
                  <p class="actor-name">{{actor.name}}</p>
                </a>
              </div>
            </div>
          </el-row>


          <!-- <div class="left-section">
            <img :src="baseUrl+movieWebVo.picUrl" :alt="movieWebVo.title" class="movie-poster">
            <h3 class="cast">Cast</h3>
            <div class="actor-list">
              <div class="actor-card" v-for="actor in movieWebVo.actorList" :key="actor.id">
                <a :href="'/actor/'+actor.actorID">
                  <img :src="baseUrl+actor.imagePath" class="actor-img">
                  <p class="actor-name">{{actor.name}}</p>
                </a>
              </div>
            </div>
          </div> -->

          <!-- <div class="movie-info">
            <h2 class="movie-title">{{movieWebVo.title}}</h2>
            <div class="movie-genre">
              <span v-for="genre in movieWebVo.genreList" :key="genre.genreID" class="genre-tag">{{genre.genreName}}</span>
            </div>
            <p class="movie-description">{{movieWebVo.overview}}</p>
            
            <div class="collect-button">
              <i class="collect-icon" v-bind:class="isCollect ? 'el-icon-star-on importicon':'el-icon-star-off'" @click.stop="changeStart"></i>
            </div>
          </div> -->


          
        </div>
      </div>

      <!--查詢評論，查詢Review 需要在Username左側顯示Avatar圖片，el-rate綁定prop="rating"-->
      <div class="">
        <el-table
          :data="reviewData"
          stripe
          style="width: 100%">
          <el-table-column
            prop="username"
            label="Name"
            width="200">
            <template slot-scope="scope">
              <img :src="scope.row.avatarPath" alt="avatar" class="avatar">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column
            prop="rating"
            label="Rating"
            width="200">
            <template slot-scope="scope">
              <el-rate
                v-model="scope.row.rating"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}">
              </el-rate>
            </template>
          </el-table-column>
          <el-table-column
            prop="reviewTime"
            label="Date"
            width="180">
          </el-table-column>
          <el-table-column
            prop="content"
            label="Comment">
          </el-table-column>

          <div style="font-size: 16px;" slot="empty">
            No comments...
          </div>
        </el-table>
      </div>

      <!--發表評論 insert到Review表格-->
      <div style="margin-top: 30px; display: flex; justify-content: left; align-items: center;">
        <div style="background-color: ; padding: 20px; border-radius: 10px;">
          <el-form :inline="true">
            <el-form-item label="">
              <el-rate style="transform: scale(1.2);" v-model="reviewBody.rating" :colors="colors"></el-rate>
            </el-form-item>
            <el-form-item label="">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="請輸入評論"
                v-model="reviewBody.content"
                style="width: 400px; font-size: 15px;"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="success" style="font-size: 18px;" @click="onSubmit">發表</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>

      
    </section>
  </div>
</template>


<script>
import movieApi from '@/api/course'
import cookie from 'js-cookie'
export default {
  data(){
    return{
      courseWebVo:{},
      movieWebVo:{},
      reviewData:[],
      baseUrl:'https://image.tmdb.org/t/p/original/',
      rating:null,
      colors: ['#99A9BF', '#F7BA2A', '#FF9900'],  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
      loginInfo:[],
      reviewBody:{},
      isCollect:0,
      movieId:0,
    }
  },
  created(){
    this.movieId = this.$route.params.id
    this.getCookieInfo()
    this.getMovieInfo()
    this.getCollectStatus()
    this.getReviews()
  },
  methods:{
    getCookieInfo(){
      var userStr = cookie.get('team9_user')
      if(userStr){
        this.loginInfo = JSON.parse(userStr)
      }
    },
    getMovieInfo(){
      movieApi.getMovieInfo(this.$route.params.id)
        .then(response =>{
          this.movieWebVo = response.data.data.movieVo
        })
    },
    getCollectStatus(){
      movieApi.isCollected(this.loginInfo.userID,this.movieId)
        .then(response=>{
          this.isCollect = response.data.data.isCollect
        })
    },
    getReviews(){
      movieApi.getReviewByMovieId(this.$route.params.id)
        .then(response=>{
          this.reviewData = response.data.data.reviews
        })
    },
    onSubmit(){
      this.reviewBody.userID = this.loginInfo.userID
      this.reviewBody.movieId = this.$route.params.id
      movieApi.sendReview(this.reviewBody)
        .then(response=>{
          this.$message({
                            type: 'success',
                            message: 'Send Success!'
                        })
          this.getReviews()
        })
    },
    changeStart() {
      let userMovieFavorite = {}
      userMovieFavorite.movieID = this.movieId
      userMovieFavorite.userID = this.loginInfo.userID
      if(this.isCollect == 1){
        movieApi.deleteCollect(userMovieFavorite)
          .then(response=>{
            this.getCollectStatus()
          })
      }else{
        movieApi.addCollect(userMovieFavorite)
          .then(response=>{
            this.getCollectStatus()
          })
      }
    },
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

.movie-genre {
  margin-top: 50px;
}

.genre-tag {
  border: 2px solid white;
  padding: 6px 11px;
  border-radius: 50px;
  font-size: 20px;
  color: #fff;
  background-color: black;
  margin-right: 12px;
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  justify-content: center; 
  align-items: center;

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
.avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 10px;
}
.collect-button{
  margin-left: 10px;
}
.collect-icon {
  transform: scale(3); /* 这将会使图标变大50% */
  cursor: pointer; /* 这行会使鼠标变为手型 */
}
</style>
