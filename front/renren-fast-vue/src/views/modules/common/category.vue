<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    :expand-on-click-node="false"
    @node-click="nodeClick">
  </el-tree>
</template>


<script>
//这里可以导入其他文件（比如组件，第三方插件js）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  //import引入的组件需要注入到对象才能用
  components: {},
  props: {},
  data() {
    //这里存放数据
    return {
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  //计算属性
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法的集合
  methods: {
    nodeClick(data,node,component){
      console.log("子组件被点击",data,node,component)
      this.$emit("node-click",data,node,component);
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/listTree'),
        method: 'get',
        params: {}
      }).then(({data}) => {
        this.menus = data.list;
      })
    },
  },
  //生命周期 -创建完成
  created() {
    this.getMenus();
  },
  //生命周期 -挂载完成
  mounted() {
  },
  //生命周期 -创建之前
  beforeCreate() {
  },
  //生命周期 -挂载之前
  beforeMount() {
  },
  //生命周期 -更新之前
  beforeUpdate() {
  },
  //生命周期 -更新之后
  updated() {
  },
  //生命周期 -销毁之前
  beforeDestroy() {
  },
  //生命周期 -销毁完成
  destroyed() {
  },
  //如果有页面有keepalive属性，这个函数会被触发
  activated() {
  }

}

</script>

<style>

</style>
