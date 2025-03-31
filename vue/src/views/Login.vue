<template>
  <div class="login-container">
    <div class="glass-panel">
      <!-- 校徽 -->
      <img src="@/assets/myschool_logo_2048px.png" alt="校徽" class="school-logo" />

      <!-- 高级动态渐变标题 -->
      <div class="dynamic-title">GDUT 宿舍管理系统 - 登录</div>

      <el-form ref="form" :model="form" :rules="rules" size="large">
        <el-form-item prop="username">
          <el-input v-model="form.username" clearable prefix-icon="avatar"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" prefix-icon="lock" show-password></el-input>
        </el-form-item>
        <el-form-item prop="identity">
          <el-radio v-model="form.identity" label="stu">学生</el-radio>
          <el-radio v-model="form.identity" label="dormManager">宿舍管理员</el-radio>
          <el-radio v-model="form.identity" label="admin">后勤管理中心</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button :disabled="!disabled" style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

  <div class="footer">©Copyright 2024-2025 AtuWeb</div>
</template>
<script src="@/assets/js/Login.js"></script>
<style scoped>

/* 应用到登录容器 */
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  /* 背景相关属性 */
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  animation: backgroundSlide 25s infinite;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 定义背景切换动画 */
@keyframes backgroundSlide {
  0% { background-image: url('/images/campus1.jpg'); }
  33% { background-image: url('/images/campus2.jpg'); }
  66% { background-image: url('/images/campus3.jpg'); }
  100% { background-image: url('/images/campus4.jpg'); }
}

/* 背景流动动画 */
@keyframes gradientFlow {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

/* 📌 玻璃拟态效果 */
.glass-panel {
  display: flex;
  flex-direction: column; /* 垂直排列 */
  align-items: center; /* 水平居中 */
  justify-content: center; /* 垂直居中 */
  background: rgba(255, 255, 255, 0.15);
  border-radius: 20px;
  padding: 30px;
  width: 400px;
  max-width: 90%;
  backdrop-filter: blur(10px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center; /* 确保所有文字内容居中 */
}

/* 🏫 校徽样式 */
.school-logo {
  width: 80px;
  height: 80px;
  margin-bottom: 10px;
}

/* 🎨 渐变动态标题 */
.dynamic-title {
  font-size: 28px;
  font-weight: bold;
  padding: 15px 0;
  text-align: center;
  background: linear-gradient(45deg, #3a8ee6, #66b1ff, deeppink);
  background-size: 300% 300%;
  -webkit-background-clip: text;
  color: transparent;
  text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
  animation: gradientAnimation 10s infinite linear;
  display: block;
  width: 100%; /* 让标题不会过窄 */
}

/* 文字渐变动画 */
@keyframes gradientAnimation {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}


/* 页脚 */
.footer {
  left: 0;
  bottom: 0;
  color: #fff;
  width: 100%;
  position: absolute;
  text-align: center;
  line-height: 30px;
  padding-bottom: 10px;
  text-shadow: #000 0.1em 0.1em 0.1em;
  font-size: 14px;
}

.footer a,
.footer span {
  color: #fff;
}
</style>
