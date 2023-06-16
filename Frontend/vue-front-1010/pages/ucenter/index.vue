<template>
  <div>
    <h2 style="text-align: center;">Create New Supplier</h2>
    <el-form label-width="100px" align-center style="margin-left: 370px;  padding-right: 430px;">
      <el-form-item label="Avatar:" >
          <el-card>
              <div style="display: flex; justify-content: center; align-items: center;">
                  <img :src="Supplier.profileUrl" alt="Product Pictures" style="max-width: 100%; max-height: 100%; border: 1px solid #ccc; width: 150px;">
              </div>
          </el-card>
      </el-form-item>


      <el-form-item label="Upload:">
          <el-upload
              class="upload-demo"
              drag
              :on-success="handlePictureSuccess"
              :before-upload="beforePictureUpload"
              :action="BASE_API+'/pmsservice/product-pic/uploadOssFile'"
              multiple
              >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">Drop file here or <em>click to upload</em></div>
              <div class="el-upload__tip" slot="tip">jpg/png files with a size less than ???kb</div>
              </el-upload>
      </el-form-item>
      


      <el-form-item>
        <el-button type="primary" @click="submitForm()">Go to list</el-button>
        <el-button @click="previous()">Previous</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import adminApi from '@/api/user'

export default {
  data() {
    return {
      supplierId:0,
      Supplier:{},
      BASE_API:process.env.BASE_API,
    };
  },
  created(){
      if(this.$route.params && this.$route.params.id){
              this.supplierId = this.$route.params.id

              this.getSupInfo()
          }
  },
  methods: {
      getSupInfo(){
      adminApi.getSupplierInfo(this.supplierId)
        .then(response=>{
            this.Supplier = response.data.supplier
            console.log(this.Supplier.profileUrl)
        })
    },
    handlePictureSuccess(res){
      this.Supplier.profileUrl = res.data.url
      adminApi.updateSupplier(this.Supplier)
        .then(response=>{
          this.$message({
                            type: 'success',
                            message: 'Update success'
                        });
        })
    },
    updateSupplier(){
      adminApi.updateSupplier(this.Supplier)
        .then(response =>{
          this.$message({
                            type: 'success',
                            message: 'Update success'
                        });
          this.$router.push({path:'/supplier/addPics/'+this.supplierId})             
        })
    },
    beforePictureUpload(file){
      // const isJPG = file.type ==='image/jpeg'
      // const isLt2M = file.size /1024/1024<2
      // if(!isJPG){
      //   this.$message.error('上传头像图片只能是JPG格式！')
      // }
      // if(!isLt2M){
      //   this.$message.error('上传头像图片大小不能超过 2MB！')
      // }
      // return isJPG && isLt2M
    },
    submitForm(){
      this.$router.push({path:'/supplier/list/'})   
    },
    previous(){
      console.log('previous')
      this.$router.push({path:'/supplier/manage/'+this.supplierId})
    }
  },
};
</script>

<style scoped>
.upload-demo {
  border: 1px dashed #409EFF;
  border-radius: 6px;
  padding: 20px 0;
  text-align: center;
  color: #999;
  cursor: pointer;
}

.upload-demo i {
  font-size: 28px;
  color: #8c939d;
  margin-bottom: 10px;
}

.upload-demo em {
  color: #999;
  font-style: normal;
}

.el-upload__tip {
  margin-top: 10px;
  font-size: 12px;
  color: #999;
}
</style>
