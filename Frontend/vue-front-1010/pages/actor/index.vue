<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- Actor List Starts -->
    <section class="container">
      <header class="comm-title all-actor-title">
        <h2 class="fl tac">
          <span class="c-333 custom-font">All Actors</span>
        </h2>
        <!-- <section class="c-tab-title">
        </section> -->
      </header>
      <!-- <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="">
          <el-input v-model="name" placeholder="Actor name"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="onSubmit">Search</el-button>
        </el-form-item>
      </el-form> -->
      <section class="c-sort-box unBr">
        <div>
          <section class="no-data-wrap" v-if="data.total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">No Data...</span>
          </section>
          <article v-if="data.total>0" class="i-teacher-list">
            <ul class="flex-container">
              <li v-for=" actor in data.items" :key="actor.actorID" class="flex-item">
                <section class="i-teach-wrap">
                  <div class="i-teach-pic">
                    <a :href="'/actor/'+actor.actorID" :title="actor.name" >
                      <img class="rounded-image" :src="baseUrl+actor.imagePath" :alt="actor.name">
                    </a>
                  </div>
                  <div class="mt10 hLh30 txtOf tac">
                    <a :href="'/actor/'+actor.actorID" :title="actor.name"  class="fsize18 c-666 movieTitleFont">{{actor.name}}</a>
                  </div>
                  <!-- <div class="hLh30 txtOf tac">
                    <span class="fsize14 c-999">{{actor.notableWorks}}</span>
                  </div> -->
                </section>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <div>
          <div class="paging">
            <a :class="{undisable: !data.hasPrevious}" href="#"  title="首页" @click.prevent="gotoPage(1)">First</a>
            <a :class="{undisable: !data.hasPrevious}" href="#" title="前一页" @click.prevent="gotoPage(data.current-1)">&lt;</a>
            <a v-for="page in data.pages" :key="page" :class="{ current: data.current == page, undisable: data.current == page}"
             href="#" :title="'第'+page+'页'" @click.prevent="gotoPage(page)">{{ page }}</a>
            <a :class="{undisable: !data.hasNext}" href="#" title="后一页" @click.prevent="gotoPage(data.current+1)">&gt;</a>
            <a :class="{undisable: !data.hasNext}" href="#"  title="末页" @click.prevent="gotoPage(data.pages)">Last</a>
            <div class="clear"></div>
          </div>
        </div>
      </section>
    </section>
    <!-- /Actor List Ends -->
  </div>
</template>


<script>
import teacherApi from '@/api/teacher'

export default {
  data(){
    return{
      data:{},
      limit:8,
      name: null,
      baseUrl:'https://image.tmdb.org/t/p/original/',
    }
  },
  created(){
    this.getActorList(1,8)
  },
  methods:{
    getActorList(page,limit){
      teacherApi.getAllActorList(page,limit)
        .then(response =>{
          this.data = response.data.data
        })
        .catch(error => {
        console.error('Error fetching actor list:', error);
        });
    },
    gotoPage(page){
      this.getActorList(page,this.limit)
    }
  },
};
</script>
<style scoped>
.search-form {
  display: flex;
  justify-content: flex-end;
  display: inline;
}

.rounded-image {
  border-radius: 20%;
}

.flex-container {
  display: flex;
  flex-wrap: wrap;
}

.flex-item {
  flex: 1 0 auto;
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