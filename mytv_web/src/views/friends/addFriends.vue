<template>
  <div class="addFriends">
    <div class="search">
      <el-input size="mini" placeholder="输入账号" v-model="searchVal">
        <template #prefix>
          <i class="el-input__icon el-icon-search"></i>
        </template>
        <template #append>
          <el-button @click="searchUser" icon="el-icon-search"></el-button>
        </template>
      </el-input>
    </div>
    <div class="result">
      <UserCard v-if="userCardShow" :user-info="userInfo"></UserCard>
      <div v-else>该用户不存在</div>
      <div>{{ userInfo }}</div>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, ref } from 'vue';
import UserCard from '@/components/userCard.vue';
import { searchUserInfo } from '@/http/api/user';

export default defineComponent({
  name: 'AddFriends',
  components: { UserCard },
  setup() {
    const userCardShow = ref(false);
    let userInfo = reactive({});
    // 搜索用户
    const searchVal = ref('');
    const searchUser = () => {
      searchUserInfo({ userName: searchVal.value }).then((res: any) => {
        if (res.code === 1) {
          userCardShow.value = true;
          userInfo = res.data;
          console.log(userInfo)
        } else {
          userCardShow.value = false;
        }
      });
    };

    return {
      userCardShow,
      searchVal,
      userInfo,
      searchUser
    };
  }
});
</script>
<style lang="less" scoped>
.addFriends {
  width: 100%;
  .search {
    width: 100%;
    padding-bottom: 10px;
    border-bottom: 1px solid #e2e2e2;
  }
  .result {
    width: 100%;
    padding-top: 10px;
  }
}
</style>
