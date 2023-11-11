<script setup>
import { Plus, Upload } from '@element-plus/icons-vue'
import {ref} from 'vue'
import avatar from '@/assets/default.png'
const uploadRef = ref()
import { ElMessage } from 'element-plus';

//获取Token
import { useTokenStore } from '@/stores/token.js';
const tokenStore = useTokenStore()



//用户头像地址


//用户头像回显
import { useUserInfoStore } from '@/stores/userinfo.js';
const userInfoStore = useUserInfoStore()

const imgUrl = ref(userInfoStore.info.userPic ? userInfoStore.info.userPic : avatar)

//图片上传成功的回调函数
const uploadSuccess = (result) => {
    imgUrl.value = result.data

    ElMessage.success(result.msg ? result.msg : "成功上传图片")
}


import { updateUserAvatarService } from '@/api/user.js';
const updateAvatar = async () => {
    let result = await updateUserAvatarService(imgUrl.value)

    ElMessage.success(result.msg ? result.msg : "更新图片成功")

    userInfoStore.info.userPic = imgUrl.value
    
}


</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>更换头像</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <el-upload 
                    auto-upload="true"
                    ref="uploadRef"
                    class="avatar-uploader" 
                    action="/api/upload"
                    :headers="{'Authorization': tokenStore.token}"
                    :show-file-list="false"
                    :on-success="uploadSuccess"
                    >
                    <img v-if="imgUrl" :src="imgUrl" class="avatar" />
                    <img v-else src="avatar" width="278" />
                </el-upload>
                <br />
                <el-button type="primary" :icon="Plus" size="large"  @click="uploadRef.$el.querySelector('input').click()">
                    选择图片
                </el-button>
                <el-button type="success" @click="updateAvatar" :icon="Upload" size="large">
                    上传头像
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
    :deep() {
        .avatar {
            width: 278px;
            height: 278px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 278px;
            height: 278px;
            text-align: center;
        }
    }
}
</style>