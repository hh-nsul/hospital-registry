<template>
  <div class="home page-component">
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in 2" :key="item">
        <img src="~assets/images/web-banner1.png" alt="">
      </el-carousel-item>
    </el-carousel>
    <!-- 搜索 -->
    <div class="search-container">
    <div class="search-wrapper">
    <div class="hospital-search">
      <el-autocomplete
      class="search-input"
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
    </div>
    <!-- bottom -->
    <div class="bottom">
    <div class="left">
    <div class="home-filter-wrapper">
    <div class="title">Hospital</div>
    <div>
      <div class="filter-wrapper">
        <span class="label">Level: </span>
        <div class="condition-wrapper">
          <!-- item.value is the variable "value" defined in com.atguigu.hospital_registry.model.dmn.Dictionary.java -->
          <span class="item v-link clickable"
          :class="hostypeActiveIndex == index ? 'selected' : ''"
          v-for="(item, index) in hostypeList" :key="item.id" 
          @click="hostypeSelect(item.value, index)" >{{item.name}}</span>
      </div>
      </div>
    <div class="filter-wrapper">
      <span
      class="label">District: </span>
        <div class="condition-wrapper">
          <span class="item v-link clickable"
          :class="provinceActiveIndex == index ? 'selected' : ''"
          v-for="(item, index) in districtList" :key="index" 
          @click="districtSelect(item.value, index)">{{item.name}} </span>
        </div>
      </div>
    </div>
    </div>
    <div class="v-scroll-list hospital-list">

      <div v-for="(item, index) in list" :key="index" class="v-card clickable list-item space" @click="show(item.hoscode)">
        <div class="">
          <div class="hospital-list-item hos-item" index="0">
          <div class="wrapper">
            <div class="hospital-title"> {{ item.hosname }} </div>
              <div class="bottom-container">
              <div class="icon-wrapper">
                <span class="iconfont"></span> {{ item.param.hospitalTypeName }}
              </div>
            <div class="icon-wrapper">
              <span class="iconfont"></span> {{ item.bookingRule.releaseTime }}
            </div>
          </div>
        </div>
        <img :src="'data:image/jpeg;base64,'+item.logoData" :alt="item.hosname" class="hospital-img">
        </div>
        </div>
      </div>

    </div>
    </div>

    <div class="right">
      <div class="common-dept">
      <div class="header-wrapper">
      <div class="title">Department</div>
      <div class="all-wrapper"><span>All</span>
      <span class="iconfont icon"></span>
      </div>
      </div>
      <div class="content-wrapper">
      <span class="item v-link clickable dark">神经内科 </span>
      <span class="item v-link clickable dark">消化内科 </span>
      <span class="item v-link clickable dark">呼吸内科 </span>
      <span class="item v-link clickable dark">内科 </span>
      <span class="item v-link clickable dark">神经外科 </span>
      <span class="item v-link clickable dark">妇科 </span>
      <span class="item v-link clickable dark"> 产科 </span>
      <span class="item v-link clickable dark">儿科 </span>
      </div>
    </div>
    <div class="space">
      <div class="header-wrapper">
      <div class="title-wrapper">
      <div class="icon-wrapper"><span
      class="iconfont title-icon"></span>
      </div>
      <span class="title">News</span>
      </div>
      <div class="all-wrapper">
      <span>All</span>
      <span class="iconfont icon"></span>
      </div>
      </div>
      <div class="content-wrapper">
      <div class="notice-wrapper">
      <div class="point"></div>
      <span class="notice v-link clickable dark">关于延长北京大学国际医院放假的通知 </span>
      </div>
      <div class="notice-wrapper">
      <div class="point"></div>
      <span class="notice v-link clickable dark">北京中医药大学东方医院部分科室医生门诊医 </span>
      </div>
      <div class="notice-wrapper">
      <div class="point"></div>
      <span class="notice v-link clickable dark"> 武警总医院号源暂停更新通知 </span>
      </div>
      </div>
    </div>
    <div class="suspend-notice-list space">
    <div class="header-wrapper">
    <div class="title-wrapper">
      <div class="icon-wrapper">
      <span class="iconfont title-icon"></span>
      </div>
      <span class="title">停诊公告</span>
      </div>
      <div class="all-wrapper">
      <span>全部</span>
      <span class="iconfont icon"></span>
      </div>
      </div>
      <div class="content-wrapper">
      <div class="notice-wrapper">
      <div class="point"></div>
      <span class="notice v-link clickable dark"> 中国人民解放军总医院第六医学中心(原海军总医院)呼吸内科门诊停诊公告 </span>
      </div>
      <div class="notice-wrapper">
      <div class="point"></div>
    <span class="notice v-link clickable dark"> 首都医科大学附属北京潞河医院老年医学科门诊停诊公告 </span>
    </div>
      <div class="notice-wrapper">
        <div class="point"></div>
        <span class="notice v-link clickable dark">中日友好医院中西医结合心内科门诊停诊公告 </span>
      </div>
    </div>
    </div>
    </div>
    </div>
  </div>
</template>
<script>

import hospitalUserApi from '@/api/hospital';
import dictionaryApi from '@/api/dictionary'

export default {
        asyncData({ params, error }) {
          //调用
          return hospitalUserApi.getHospitalList(1, 10, null)
            .then(response => {
              return {
                list: response.data.content,
                pages: response.data.totalPages
              }
            })
        },

  data() {
    return {
      searchObj: {},
      page: 1,
      limit: 10,

      hosname: '', //医院名称
      hostypeList: [], //医院等级集合
      districtList: [], //地区集合

      hostypeActiveIndex: 0,
      provinceActiveIndex: 0
    }
  },

  created() {
    this.init()
  },

  methods: {
    init() {
      dictionaryApi.getSubNodesByDictCode('Hostype')
                   .then(response => {
                     this.hostypeList = []
                     this.hostypeList.push({"name":"All", "value":""})
                     for (var i = 0; i < response.data.length; ++i) {
                       this.hostypeList.push(response.data[i])
                       console.log("response.data[i]: " + response.data[i])
                     }
                   }),

      dictionaryApi.getSubNodesByDictCode('Beijing')
                   .then(response => {
                     this.districtList = []
                     this.districtList.push({"name":"All", "value":""})
                     for (let i in response.data) {
                       this.districtList.push(response.data[i])
                       console.log("response.data[i]: " + response.data[i])
                     }
                   })

    },

    getHospitalList() {
      hospitalUserApi.getHospitalList(this.page, this.limit, this.searchObj)
                     .then(response => {
                       for (let i in response.data.content) {
                         this.list.push(response.data.content[i])
                       }

                       this.page = response.data.totalPages
                     })
    },

    hostypeSelect(hostype, index) {
      this.list = []
      this.page = 1
      this.hostypeActiveIndex = index
      this.searchObj.hostype = hostype

      this.getHospitalList()
    },

    districtSelect(districtCode, index) {
      this.list = []
      this.page = 1
      this.provinceActiveIndex = index
      this.searchObj.districtCode = districtCode

      this.getHospitalList()
    },

    querySearchAsync(queryString, cb) {
      this.searchObj = []
      if(queryString == '') return
      hospitalUserApi.getHospitalByName(queryString).then(response => {
        for (let i = 0, len = response.data.length; i <len; i++) {
          response.data[i].value = response.data[i].hosname
        }
        cb(response.data)
      })
    },

    //在下拉框选择某一个内容，执行下面方法，跳转到详情页面中
    handleSelect(item) {
      // By default, it's going to access "index.vue" under "/pages/hospital" directory
      window.location.href = '/hospital/' + item.hoscode
      consloe.log("handleSelect window.location.href: " + window.location.href)
    },

    //点击某个医院名称，跳转到详情页面中
    show(hoscode) {
      window.location.href = '/hospital/' + hoscode
      consloe.log("show window.location.href: " + window.location.href)
    }
  }

}
</script>