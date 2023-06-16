import request from '@/utils/request'

export default{
    getTopCourseTeacher(){
        return request({
            url:`/eduservice/indexfront/index`,
            method:'get'
        })
    }
}