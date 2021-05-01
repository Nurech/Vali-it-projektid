<template>
  <div class="lesson1">
    <h1>This is an lesson page</h1>
    <br>
    <input v-model.number="x" placeholder="Insert X"/>
    <input v-model.number="y" placeholder="Insert Y"/>
    <button class="btn btn-success" v-on:click="show()">CALCULATE</button>

    <table border="1">
      <tr v-for="row in table">
        <td v-for="column in row">{{column}}</td>
      </tr>
    </table>
    <br>
    <input v-model="accountNumber" placeholder="Insert account number"/>
    <input v-model="balance" placeholder="Insert initial balance"/>
    <br>
    <button class="btn btn-success" v-on:click="createAccount()">CREATE ACCOUNT</button>
    <br>
    <button class="btn btn-success" v-on:click="getBalance()">GET BALANCE</button>
    <br>
    <button class="btn btn-success" v-on:click="getAll()">GET ALL</button>
    <br>
    <button class="btn btn-success" v-on:click="deposit()">DEPOSIT</button>
    <br>
    <br>
    <textarea class="alert alert-primary" placeholder="Your server reply will show here...">{{serverAnswer}}{{serverError}}</textarea>
    <br>

  </div>
</template>

<script>
import {post} from "axios";
import {get} from "axios";


export default {
  data: function (){
    return {
      "x": "",
      "y": "",
      "table": [[]],
      "balance": "",
      "accountNumber": "",
      "serverAnswer": "",
      "serverError": ""
    }
  },

  methods: {
    "show":  function (){
      for (let i = 1; i <= this.x; i++){
        this.table[i-1] = []
        for (let j = 1; j <= this.y; j++){
          this.table[i-1][j-1] = i *j;
        }
      }
      this.table.splice()
    },

    "createAccount": function (){
      post('/api/createAccount', {
        accountNumber: this.accountNumber,
        balance: this.balance
      })
          .then((response) => {
            this.serverAnswer = response.data;
          }).catch((error) => {
        this.serverAnswer = error.response.data.message;
      });
    },


    "getBalance": function (){
      let accountNumber = this.accountNumber;
      get('/api/balance/' + accountNumber)
          .then((response) => {
            this.serverAnswer = response.data;
          }).catch((error) => {
        this.serverAnswer = error.response.data.message;
      });
    },

    "getAll": function (){
      get('/api/allAccounts')
          .then((response) => {
            this.serverAnswer = response.data;
          }).catch((error) => {
        this.serverAnswer = error.response.data.message;
      });
    },
    "deposit": function (){
      post('/api/deposit', {
        accountNumber: this.accountNumber,
        balance: this.balance
      })
          .then((response) => {
            this.serverAnswer = response.data;
          }).catch((error) => {
        this.serverAnswer = error.response.data.message;
      });
    }
  }
}
</script>
