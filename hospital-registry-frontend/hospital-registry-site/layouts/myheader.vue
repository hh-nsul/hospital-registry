<template>
    <div class="header-container">
        <div class="wrapper">
        <!-- logo -->
            <div class="left-wrapper v-link selected">
                <img style="width: 50px" width="50" height="50" src="~assets/images/logo.png">
                <span class="text">Hospital Registry Platform</span>
            </div>

        <div class="search-wrapper">
            <div class="hospital-search animation-show">
                <el-autocomplete
                    class="search-input small"
                    prefix-icon="el-icon-search"
                    v-model="state"
                    :fetch-suggestions="querySearchAsync"
                    placeholder="Hospital Name"
                    @select="handleSelect"
                    >
                    <span slot="suffix" class="search-btn v-link highlight clickable selected">Search</span>
                </el-autocomplete>
            </div>
        </div>

        <!-- Right side -->
        <div class="right-wrapper">
        <span class="v-link clickable">Help</span>
        <!--        <el-dropdown >-->
        <!--              <span class="el-dropdown-link">-->
        <!--                晴天<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>-->
        <!--              </span>-->
        <!--            <el-dropdown-menu class="user-name-wrapper" slot="dropdown">-->
        <!--                <el-dropdown-item>挂号订单</el-dropdown-item>-->
        <!--                <el-dropdown-item>就诊人管理</el-dropdown-item>-->
        <!--                <el-dropdown-item divided>退出登录</el-dropdown-item>-->
        <!--            </el-dropdown-menu>-->
        <!--        </el-dropdown>-->
        <span class="v-link clickable" @click="dialogUserFormVisible = true">Sign Up/Sign In</span>
        </div>
        </div>
    </div>
</template>
<script>
export default {
}
</script>