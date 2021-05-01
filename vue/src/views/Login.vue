<template>
  <div class="login">
    <h1>This is an login page</h1>
    <br>
    <input v-model="username" placeholder="Insert username"/>
    <input v-model="password" placeholder="Insert password"/>
    <br>
    <button class="btn btn-success" v-on:click="login()">LOGIN</button> |
    <button class="btn btn-primary" v-on:click="register()">REGISTER</button> |
    <button class="btn btn-danger" v-on:click="logout()">LOGOUT</button>
    <br>
    <textarea class="alert alert-primary" placeholder="Your server reply will show here...">{{serverAnswer}}</textarea>
    <br>
  </div>
</template>

<script>
import {post} from "axios";

// import axios, {AxiosResponse} from 'axios'
//
// const axiosApi = axios.create({
//   baseURL: `/public`,
//   timeout: 1000,
//   headers: { 'Content-Type': 'application/json' }
// });
//
// export default {
//     login(): Promise<AxiosResponse<string>> {
//     return axiosApi.get(`/login/`);
//   },

export default {
  data: function () {
    return {
      "username": "",
      "password": "",
      "serverAnswer": ""
    }
  },

  methods: {

    "logout": function () {
      localStorage.removeItem('user-token') // remove on logout
      this.$forceUpdate();
      },

    "login": function () {
      post('/public/login', {
        username: this.username,
        password: this.password
      })
          .then((response) => {
            this.serverAnswer = response.data;
            let token = response.data;
            localStorage.setItem("user-token", token) // store the token
            this.$http.defaults.headers.common["Authorization"] = "Bearer " + token
          }).catch((error) => {
            this.serverAnswer = error.response.data.message;
          });
    },

    "register": function () {
      post('/public/register', {
        username: this.username,
        password: this.password
      })
          .then((response) => {
            this.serverAnswer = response.data;
          }).catch((error) => {
        this.serverAnswer = error.response.data.message;
      });
    },
  }
}
</script>
