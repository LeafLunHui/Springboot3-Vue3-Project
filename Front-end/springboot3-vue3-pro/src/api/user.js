import request from '@/utils/request'

//注册服务
export const userRegisterService = (registerData) => {
    const params = new URLSearchParams()
    for (let key in registerData){
        params.append(key,registerData[key])
    }
    return request.post('/user/register',params);
}


//登录服务
export const userLoginService = (LoginData) => {
    const params = new URLSearchParams()
    for (let key in LoginData){
        params.append(key,LoginData[key])
    }
    return request.post('/user/login',params);
}

//获取用户详细信息
export const userInfoService = () => {
    return request.get('/user/userInfo')
}

//更新用户信息服务
export const updateUserInfoService = (UserInfoData) => {
    return request.put('/user/update',UserInfoData)
}

//更新用户头像
export const updateUserAvatarService = (avatarUrl) => {
    return request.patch('/user/updateAvatar?avatarUrl=' + avatarUrl)
}

//修改用户密码
export const resetUserPasswordService = (resetPasswordData) => {
    return request.patch('/user/updatePwd',resetPasswordData)
}