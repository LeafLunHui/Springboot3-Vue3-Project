<script setup>
import { ref } from 'vue'

const resetPasswordData = ref({
    old_pwd : '',
    new_pwd : '',
    re_pwd : ''
})

const checkRePassword = (rule,value,callback)=>{
    if( value === ''){
        callback(new Error("请重复一遍新密码"))
    }else if(value !== resetPasswordData.value.new_pwd){
        callback(new Error("两次输入密码不一致"))
    }else{
        callback()
    }
}

const rules = {
    old_pwd: [
        { required: true, message: '请输入旧密码', trigger: 'blur' },
        { min: 5, max: 16, message: '本数据长度必需为5~6位', trigger: 'blur' }
    ],
    new_pwd: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { min: 5, max: 16, message: '本数据长度必需为5~6位', trigger: 'blur' }
    ],
    re_pwd: [
        { validator: checkRePassword, trigger: 'blur' }
    ]
}

//提交本次密码更新
import { resetUserPasswordService } from '@/api/user.js'
import { useTokenStore } from '@/stores/token.js';
import { useUserInfoStore } from '@/stores/userinfo.js'
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
const router = useRouter()
const tokenStore = useTokenStore()
const userInfoStore = useUserInfoStore()
const commitResetPassword = async () => {
    let result = await resetUserPasswordService(resetPasswordData.value)

    ElMessage.success( result.msg ? result.msg : "重置密码成功!" )

    tokenStore.removeToken
    userInfoStore.removeInfo

    ElMessage.success("请重新登录" )
    router.push('/login')
}

</script>


<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>修改密码</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-form :model="resetPasswordData" :rules="rules" label-width="100px" size="large">
                    <el-form-item label="旧密码" prop="old_pwd">
                        <el-input v-model="resetPasswordData.old_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="新密码" prop="new_pwd">
                        <el-input v-model="resetPasswordData.new_pwd"></el-input>
                    </el-form-item>
                    <el-form-item label="重复新密码" prop="re_pwd">
                        <el-input v-model="resetPasswordData.re_pwd"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="commitResetPassword">提交修改</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </el-card>
</template>
