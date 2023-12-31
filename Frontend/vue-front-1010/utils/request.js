import axios from 'axios'
import cookie from 'js-cookie'
import { Message, MessageBox } from 'element-ui'

const service = axios.create({
    baseURL:'http://localhost:8201',
    timeout:20000 //请求超过的时间
})

service.interceptors.request.use(
    config =>{
        if(cookie.get('team9_token')){
            //get cookie information and put it in header
            config.headers['token'] = cookie.get('team9_token');
        }
        return config
        },
        err =>{
            return Promise.reject(err);
        }
)

// http response 拦截器
service.interceptors.response.use(
    response => {
      //debugger
      if (response.data.code == 28004) {
          console.log("response.data.resultCode是28004")
          // 返回 错误代码-1 清除ticket信息并跳转到登录页面
          //debugger
          window.location.href="/login"
          return
      }else{
        if (response.data.code !== 20000) {
          //25000：订单支付中，不做任何提示
          if(response.data.code != 25000) {
            Message({
              message: response.data.message || 'error',
              type: 'error',
              duration: 5 * 1000
            })
          }
        } else {
          return response;
        }
      }
    },
    error => {
      console.log('err' + error) // for debug
      Message({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error.response)   // 返回接口返回的错误信息
  });
  
export default service