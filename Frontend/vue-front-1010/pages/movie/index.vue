<template>
    <div id="aCoursesList" class="bg-fa of">
      <!-- /movie list 开始 -->
      <section class="container">
        <header class="comm-title">
          <h2 class="fl tac">
            <span class="c-333 custom-font">All Movies</span>
          </h2>
        </header>
        <section class="c-sort-box">
          <section class="c-s-dl">
            <dl>
              <dt>
                <span class="c-999 category">Categories:</span>
              </dt>
              <dd class="c-s-dl-li">
                <ul class="clearfix">
                  <li>
                    <a title="total" href="#" @click="seachAll()" style="font-size: 18px; font-weight: bold; margin-left: 25px;">All</a>
                  </li>
                  <li v-for="(item,index) in cateList" :key="index" :class="{active:oneIndex==index}">
                    <a :title="item.title" href="#" @click="searchOne(item.genreID,index)" class="cate">{{ item.genreName }}</a>
                  </li>
                  
                </ul>
              </dd>
            </dl>
            <div class="clear"></div>
          </section>
          <div class="js-wrap">
            <section class="fr">
              <span class="c-ccc">
                <i class="c-master f-fM">1</i>/
                <i class="c-666 f-fM">1</i>
              </span>
            </section>
            <section class="fl">
              <ol class="js-tap clearfix" >

                <li :class="{'current bg-orange':rateAvg!=''}">
                  <a title="Rate" href="javascript:void(0);" @click="serchRateAvg()" class="rankFont">Rate
                  <span :class="{hide:rateAvg==''}">↓</span>
                  </a>
                </li>
                <!-- <li :class="{'current bg-orange':rateCount!=''}">
                  <a title="Rate Amount" href="javascript:void(0);" @click="serchRateCount()" style="font-size: 18px;">Rate Amount
                  <span :class="{hide:rateCount==''}">↓</span>
                  </a>
                </li> -->
                <li :class="{'current bg-orange':gmtCreateSort!=''}">
                  <a title="Release Date" href="javascript:void(0);" @click="serchGmtCreateSort()" class="rankFont">Release Date
                  <span :class="{hide:gmtCreateSort==''}">↓</span>
                  </a>
                </li>
                <li >
                  <el-button type="" @click="clearAndSearch()" class="rankFont">Clear</el-button>
                </li>
              </ol>
            </section>
          </div>
          <div class="mt40">
            <!-- /无数据提示 开始-->
            <section class="no-data-wrap" v-if="data.total == 0">
              <em class="icon30 no-data-ico">&nbsp;</em>
              <span class="c-666 fsize16 ml10 vam">No data...</span>
            </section>
            <!-- /无数据提示 结束-->
            <article class="comm-course-list" v-if="data.total > 0">
              <ul class="of" id="bna">
                <li v-for="item in data.items" :key="item.movieID">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img :src="baseUrl+item.picUrl" class="img-responsive" :alt="item.title">
                      <div class="cc-mask">
                        <a :href="'/movie/'+item.movieID" title="watch" class="comm-btn c-btn-1">watch</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="'/movie/'+item.movieID" :title="item.title" class="course-title fsize20 c-333 movieTitleFont">{{ item.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <!-- <span class="fr jgTag bg-green">
                        <i class="c-fff fsize12 f-fA">{{ Number(item.price) === 0 ?"免費":"付費" }}</i>
                      </span> -->
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA" style="font-size: 15px;">Official Rate: {{ item.rateAvg }}</i>
                        |
                        <i class="c-999 f-fA" style="font-size: 15px;">{{ item.releaseDate | formatDate }}</i>
                      </span>
                    </section>
                  </div>
                </li>
                
              </ul>
              <div class="clear"></div>
            </article>
          </div>
          <!-- 公共分页 开始 -->
          <div>
            <div class="paging">
            <!-- undisable这个class是否存在，取决于数据属性hasPrevious -->
            <a :class="{undisable: !data.hasPrevious}" href="#"  title="首页" @click.prevent="gotoPage(1)">First</a>
            <a :class="{undisable: !data.hasPrevious}" href="#" title="前一页" @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a v-for="page in data.pages" :key="page" :class="{ current: data.current == page, undisable: data.current == page}"
             href="#" :title="'第'+page+'页'" @click.prevent="gotoPage(page)">{{ page }}</a>
            <a :class="{undisable: !data.hasPrevious}" href="#" title="后一页" @click.prevent="gotoPage(data.current+1)">></a>
            <a :class="{undisable: !data.hasNext}" href="#"  title="末页" @click.prevent="gotoPage(data.pages)">Last</a>
            <div class="clear"></div>
          </div>
          </div>
          <!-- 公共分页 结束 -->
        </section>
      </section>
      <!-- /课程列表 结束 -->
    </div>
  </template>
  <script>
  import courseApi from '@/api/course'
  export default {
    data(){
      return{
        page:1,
        limit:4,
        data:{},
        subSubjectNestedList:[],
        subSubjectList:[],
        baseUrl:'https://image.tmdb.org/t/p/original/',
        cateList:[],
        searchObj:{},
        oneIndex:-1,
        twoIndex:-1,
        gmtCreateSort:'',
        rateAvg:'',
        rateCount:''
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
      this.initCourseFirst()
      this.initCate()
    },
    methods:{
      //1.查詢第一頁數據
      initCourseFirst(){
          courseApi.getMovieList(1,4,this.searchObj)
            .then(response =>{
              this.data = response.data.data
            })
        },
      initCate(){
      courseApi.getAllCate()
        .then(response =>{
          this.cateList = response.data.data.list
        })
      },
      seachAll(){
        this.searchObj.genreID = ''
        this.gotoPage(1)
      },
      clearAndSearch(){
        this.gmtCreateSort=""
        this.rateAvg = ""
        this.rateCount = ""

        this.searchObj.gmtCreateSort = this.gmtCreateSort

        this.searchObj.rateAvg = this.rateAvg

        this.searchObj.rateCount = this.rateCount
        this.gotoPage(1)
      },
      getCourseList(page, limit,searchObj){
        courseApi.getMovieList(page, limit, searchObj)
          .then(response =>{
            this.data = response.data.data
          })
      },
      gotoPage(page){
        this.getCourseList(page,this.limit,this.searchObj)
      },
      searchOne(genreID,index){
        this.searchObj.genreID = genreID
        this.gotoPage(1)
      },
      serchRateCount(){
        this.gmtCreateSort = ""
        this.rateAvg=""
        this.rateCount = "1"

        this.searchObj.gmtCreateSort = this.gmtCreateSort
        this.searchObj.rateAvg = this.rateAvg
        this.searchObj.rateCount = this.rateCount
        this.gotoPage(1)
      },
      serchRateAvg(){
        this.gmtCreateSort = ""
        this.rateAvg="1"
        this.rateCount = ""

        this.searchObj.gmtCreateSort = this.gmtCreateSort

        this.searchObj.rateAvg = this.rateAvg

        this.searchObj.rateCount = this.rateCount
        this.gotoPage(1)
      },
      serchGmtCreateSort(){
        this.gmtCreateSort="1"
        this.rateAvg = ""
        this.rateCount = ""

        this.searchObj.gmtCreateSort = this.gmtCreateSort

        this.searchObj.rateAvg = this.rateAvg

        this.searchObj.rateCount = this.rateCount
        this.gotoPage(1)
      }
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
  .category{
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
    font-size: 20px; /* 修改为您想要的字体大小 */
    font-weight: bold; /* 修改为您想要的字体粗细 */
    white-space: nowrap;
  }

.cate{
  font-size: 22px; 
  margin-left: 22px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
.rankFont{
  font-size: 23px; 
  margin-left: 22px;
  color:cadetblue;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.movieTitleFont{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif; 
  font-size: 22px;
}
</style>