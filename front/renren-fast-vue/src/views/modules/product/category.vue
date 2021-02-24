<template>
  <div>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree :data="menus" :props="defaultProps" @node-click="handleNodeClick"
             :expand-on-click-node="false" show-checkbox node-key="catId"
             :default-expanded-keys="expandedKey"
             ref="menuTree">
    <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            v-if="node.level <= 2"
            type="text"
            size="mini"
            @click="() => append(data)">
            Append
          </el-button>
          <el-button
            v-if="node.childNodes.length==0"
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            Delete
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => edit(data)">
            edit
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <el-form :model="category">
        <el-form-item label="分类名称" :label-width="formLabelWidth">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类图标" :label-width="formLabelWidth">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" :label-width="formLabelWidth">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitData">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>


<script>
//这里可以导入其他文件（比如组件，第三方插件js）
//例如：import 《组件名称》 from '《组件路径》';

export default {
  //import引入的组件需要注入到对象才能用
  comments: {},
  props: {},
  data() {
    return {
      editType: "",
      category: {name: "", parentCid: 0, catLevel: 0, icon: "", productUnit: ""},
      editCategory: {},
      formLabelWidth: '120px',
      menus: [],
      expandedKey: [],
      dialogVisible: false,
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
    batchDelete() {
      let checkNodes = this.$refs.menuTree.getCheckedNodes();
      let ids = []
      checkNodes.forEach(n=>{
        ids.push(n.catId)
      })
      this.$http({
        url: this.$http.adornUrl('/product/category/delete'),
        method: 'post',
        data: this.$http.adornData(ids, false)
      }).then(({data}) => {
        this.dialogVisible = false
        this.getMenus()
      })
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
    handleNodeClick(data) {
      this.data = data;
    },
    append(data) {
      this.editType = "edit"
      this.dialogVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
      // this.category.icon = ""
      // this.category.productUnit = ""
      // this.category.name = ""
    },
    addMenu() {
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.dialogVisible = false
        this.getMenus()
      })
    },
    edit(data) {
      this.editType = "edit"
      this.dialogVisible = true
      this.category.name = data.name
      this.editCategory.catId = data.catId
      this.category.icon = data.icon
      this.category.productUnit = data.productUnit
    },
    submitData() {
      if (this.editType == "add") {
        this.addMenu()
      }
      if (this.editType == "edit") {
        this.editCategory.name = this.category.name
        this.editCategory.icon = this.category.icon
        this.editCategory.productUnit = this.category.productUnit
        this.$http({
          url: this.$http.adornUrl('/product/category/update'),
          method: 'post',
          data: this.$http.adornData(this.editCategory, false)
        }).then(({data}) => {
          this.dialogVisible = false
          this.getMenus()
        })
      }

    },
    remove(node, data) {
      let ids = [data.catId]
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          this.getMenus()
          this.$message({
            message: "操作成功",
            type: "success"
          })
          this.expandedKey = [node.parent.data.catId]
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    }
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
