<template>
  <div class="flame">
    <div class="flame1"></div>
    <div class="flame2"></div>
    <div class="flame3"></div>
    <div class="flame4"></div>
    <div class="flame5"></div>
    <div class="flame6"></div>
    <div class="flame7"></div>
    <div class="flame8"></div>
    <div class="branch1"></div>
    <div class="branch2"></div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'HelloWorld',
  props: {
    msg: String
  }
})
</script>

<style scoped>
.flame {
  width: 300px;
  height: 300px;
  position: relative;
  margin: 0 auto;
}
.branch1,
.branch2 {
  width: 200px;
  height: 20px;
  background: #6d3b03;
  position: absolute;
  bottom: 20px;
  left: 55px;
}
.branch1 {
  transform: rotateZ(15deg);
}

.branch2 {
  transform: rotateZ(-15deg);
}
/* /火焰效果，用简单div实现/  */
.flame div:nth-child(-n + 6) {
  width: 0px;
  height: 0px;
  background: #e95022;
  border-radius: 20%;
  transform: rotateZ(45deg);
  position: absolute;
  bottom: 25px;
  left: 50%;
  transform: translateX(-50%)
}

.flame div:nth-child(1) {
  animation: gun 6s linear Infinite;
}

.flame div:nth-child(2) {
  animation: gun1 6s linear Infinite;
  animation-delay: 1s;
}

.flame div:nth-child(3) {
  animation: gun 6s linear Infinite;
  animation-delay: 2s;
}
.flame div:nth-child(4) {
  animation: gun1 6s linear Infinite;
  animation-delay: 3s;
}
.flame div:nth-child(5) {
  animation: gun 6s linear Infinite;
  animation-delay: 4s;
}
.flame div:nth-child(6) {
  animation: gun1 6s linear Infinite;
  animation-delay: 5s;
}
/* /添加伪类元素实现火花效果/  */
.flame1::before {
  content: '';
  width: 0px;
  height: 0px;
  background: #ffc001;
  border-radius: 50%;
  transform: rotateZ(45deg);
  position: absolute;
  left: 45%;
  top: 60%;
  animation: gun2 4s ease-in Infinite;
  animation-delay: 1s;
  background: radial-gradient(5px at center, rgb(240, 96, 13), #ffc001);
}
.flame3::before {
  content: '';
  width: 0px;
  height: 0px;
  background: #ffc001;
  border-radius: 50%;
  transform: rotateZ(45deg);
  position: absolute;
  left: 45%;
  top: 60%;
  animation: gun2 4s ease-in Infinite;
  animation-delay: 3s;
  background: radial-gradient(5px at center, red, #ffc001);
}
.flame5::before {
  content: '';
  width: 0px;
  height: 0px;
  background: #ffc001;
  border-radius: 50%;
  transform: rotateZ(45deg);
  position: absolute;
  left: 45%;
  top: 60%;
  animation: gun2 4s ease-in Infinite;
  animation-delay: 6s;
  background: radial-gradient(5px at center, red, #ffc001);
}
@keyframes gun {
  0% {
    width: 10px;
    height: 10px;
    transform: translateY(0px) translateX(0px) rotateZ(45deg);
  }
  100% {
    width: 40px;
    height: 40px;
    transform: translateY(-200px) translateX(-30px) rotateZ(45deg);
    opacity: 0.2;
  }
}

@keyframes gun1 {
  0% {
    width: 10px;
    height: 10px;
    transform: translateY(0px) translateX(0px) rotateZ(45deg);
  }
  100% {
    width: 40px;
    height: 40px;
    transform: translateY(-200px) translateX(30px) rotateZ(45deg);
    opacity: 0.2;
  }
}

@keyframes gun2 {
  0% {
    width: 3px;
    height: 3px;
    transform: translateY(0px) translateX(0px) rotateZ(45deg);
  }
  50% {
    width: 5px;
    height: 5px;
    transform: translateY(-130px) translateX(-30px) rotateZ(45deg);
  }
  100% {
    width: 5px;
    height: 5px;
    transform: translateY(-200px) translateX(-70px) rotateZ(45deg);
  }
}
</style>
