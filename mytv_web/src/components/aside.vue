<template>
  <div class="aside">
    <div class="header">
      <el-avatar shape="square" :src="squareUrl"></el-avatar>
    </div>
    <div class="menu">
      <ul>
        <li><i class="el-icon-user"></i></li>
        <li><i class="el-icon-video-camera"></i></li>
      </ul>
    </div>
    <div class="bootom-menu">
      <ul>
        <li><i class="el-icon-menu"></i></li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'Aside'
});
</script>
<style lang="less" scoped>
.aside {
  width: 65px;
  height: 100%;
  background: rgb(54, 54, 54);
  padding-top: 20px;
  padding-bottom: 150px;
  position: relative;
  .header {
    width: 100%;
    padding-left: 10px;
  }
  .menu {
    width: 100%;
    padding-top: 20px;
    ul {
      width: 100%;
      li {
        width: 100%;
        height: 65px;
        padding: 17px 17px;
        .active {
          color: #fff;
        }
        i {
          cursor: pointer;
          font-size: 25px;
          color: #e2e2e2;
        }
      }
    }
  }
  .bootom-menu {
    position: absolute;
    bottom: 10px;
    left: 0;
    ul {
      width: 100%;
      li {
        width: 100%;
        height: 40px;
        padding: 5px 17px;
        .active {
          color: #fff;
        }
        i {
          cursor: pointer;
          font-size: 25px;
          color: #e2e2e2;
        }
      }
    }
  }
}
</style>
