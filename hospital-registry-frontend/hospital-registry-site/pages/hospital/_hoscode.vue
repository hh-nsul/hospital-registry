<template>
<!-- header -->
<div class="nav-container page-component">
<!--左侧导航 #start -->
<div class="nav left-nav">
<div class="nav-item selected">
<span class="v-link selected dark" 
 :onclick="'javascript:window.location=\'/hospital/'+hospital.hoscode+'\''">Registry</span>
</div>
<div class="nav-item ">
<span class="v-link clickable dark" :onclick="'javascript:window.location=\'/hospital/detail/'+hospital.hoscode+'\''">Detail</span>
</div>
<div class="nav-item">
<span class="v-link clickable dark" :onclick="'javascript:window.location=\'/hospital/notice/'+hospital.hoscode+'\''">Notice</span>
</div>
<div class="nav-item "><span
class="v-link clickable dark">Suspension</span>
</div>
<div class="nav-item "><span
class="v-link clickable dark">Query/Cancel</span>
</div>
</div>
<!-- 左侧导航 #end -->
<!-- 右侧内容 #start -->
<div class="page-container">
<div class="hospital-home">
<div class="common-header">
<div class="title-wrapper"><span class="hospital-title">{{ hospital.hosname }}</span>
<div class="icon-wrapper">
<span class="iconfont"></span>{{ hospital.param.hospitalTypeName }}
</div>
</div>
</div>
<div class="info-wrapper">
<img class="hospital-img" :src="'data:image/jpeg;base64,'+hospital.logoData" :alt="hospital.hosname">
<div class="content-wrapper">
<div> Registry Info </div>
<div class="line">
<div><span class="label">预约周期: </span><span>{{ bookingRule.cycle }}天</span></div>
<div class="space"><span class="label">放号时间: </span><span>{{ bookingRule.releaseTime }}</span></div>
<div class="space"><span class="label">停挂时间: </span><span>{{ bookingRule.stopTime }}</span></div>
</div>
<div class="line"><span class="label">退号时间: </span>
<span v-if="bookingRule.quitDay == -1">就诊前一工作日{{ bookingRule.quitTime }}前取消</span>
<span v-if="bookingRule.quitDay == 0">就诊前当天{{ bookingRule.quitTime }}前取消</span>
</div>
<div style="margin-top:20px">How to Register</div>
<div class="rule-wrapper">
<ol>
<li v-for="item in bookingRule.rule" :key="item">{{ item }}</li>
</ol>
</div>
</div>
</div>
<div class="title select-title">Department</div>
<div class="select-dept-wrapper">
<div class="department-wrapper">
<div class="hospital-department">
<div class="dept-list-wrapper el-scrollbar" style="height: 100%;">
<div class="dept-list el-scrollbar__wrap" style="margin-bottom: -17px; margin-right: -17px;">
<div class="el-scrollbar__view">
<div class="sub-item" v-for="(item,index) in departmentVoList" :key="item.id" :class="index == activeIndex ? 'selected' : ''" @click="move(index,item.depcode)"> {{ item.depname }}</div>
</div>
</div>
<div class="el-scrollbar__bar is-horizontal">
<div class="el-scrollbar__thumb" style="transform: translateX(0%);"></div>
</div>
<div class="el-scrollbar__bar is-vertical">
<div class="el-scrollbar__thumb" style="transform: translateY(0%); height: 91.4761%;"></div>
</div>
</div>
</div>
</div>
<div class="sub-dept-container">
<div v-for="(item,index) in departmentVoList" :key="item.id" :class="index == 0 ? 'selected' : ''" class="sub-dept-wrapper" :id="item.depcode">
<div class="sub-title">
<div class="block selected"></div>{{ item.depname }}
</div>
<div class="sub-item-wrapper">
<div v-for="it in item.children" :key="it.id" class="sub-item" @click="schedule(it.depcode)"><span class="v-link clickable">{{ it.depname }} </span></div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- 右侧内容 #end -->
</div>
<!-- footer -->
</template>

<script>

import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'

import hospitalApi from '@/api/hospital'

export default {
  data() {
    return {
      hoscode: null,
      activeIndex: 0,

      hospital: {
        param: {}
      },
      bookingRule : {},
      departmentVoList : []
    }
  },

  created() {

    // Retrieve hoscode from URL ptah
    this.hoscode = this.$route.params.hoscode
    this.init()
  },

  methods: {
    init() {
      hospitalApi.getHospitalDetailByHoscode(this.hoscode)
                 .then(response => {
                  // com.atguigu.hospital_registry.service.impl.HospitalServiceImpl#getHospitalDetailByHoscode
                  // map.put("hospital", hospital);
                  // map.put("bookingRule", hospital.getBookingRule());
                   this.hospital = response.data.hospital
                   this.bookingRule = response.data.bookingRule
                 })

      hospitalApi.getDepartmentByHoscode(this.hoscode)
                 .then(response => {
                   this.departmentVoList = response.data
                 })
    },

    move(index, depcode) {
      this.activeIndex = index
      document.getElementById(depcode).scrollIntoView();
    }
  }


}
</script>