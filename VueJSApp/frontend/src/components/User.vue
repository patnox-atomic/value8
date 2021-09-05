<template>
  <v-container>
    <h1>User Management</h1>
    <p>Users</p>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          You have successfully added an user
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>User Creation</h3>
        <v-text-field
          v-model="userCreation.firstname"
          label="First Name"
        ></v-text-field>
        <v-text-field
          v-model="userCreation.lastname"
          label="Last Name"
        ></v-text-field>
        <v-text-field
          v-model="userCreation.email"
          label="Email"
        ></v-text-field>
        <v-text-field
          v-model="userCreation.password"
          label="Password"
        ></v-text-field>
        <v-btn color="blue" v-on:click="createUser">
          Create User
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Users</h3>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  ID
                </th>
                <th class="text-left">
                  First Name
                </th>
                <th class="text-left">
                  Last Name
                </th>
                <th class="text-left">
                  Email
                </th>
                <th class="text-left">
                  User Role
                </th>
                <th class="text-left">
                  Locked
                </th>
                <th class="text-left">
                  Enabled
                </th>
                <th class="text-left">
                  User Name
                </th>
                <th class="text-left">
                  Not Expired
                </th>
                <th class="text-left">
                  Not Locked
                </th>
                <th class="text-left">
                  Credentials Not Expired
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="user in users" :key="user.id">
                <td>{{ user.id }}</td>
                <td>{{ user.firstName }}</td>
                <td>{{ user.lastName }}</td>
                <td>{{ user.email }}</td>
                <td>{{ user.appUserRole }}</td>
                <td>{{ user.locked }}</td>
                <td>{{ user.enabled }}</td>
                <td>{{ user.username }}</td>
                <td>{{ user.accountNonExpired }}</td>
                <td>{{ user.accountNonLocked }}</td>
                <td>{{ user.credentialsNonExpired }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import api from "@/service/apiService";

export default 
{
    name: "User",
    data: vm => ({
        userCreation: 
        {
            firstname: "",
            lastname: "",
            email: "",
            password: "",
        },
        users: [],
        responseSuccess: false,
    }),
    methods: {
        readUsers: async function() {
            const data = await api.readUsers();
            this.users = data;
            //console.log('Users 1:: ' + JSON.stringify(this.users));
        },
        getData() {
            this.$http.get('/user', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            })
            .then(response => {
                ////this.total_items = Number(response.data.meta.querytotal);
                ////this.payload = response.data.data;
                ////this.loading = false;
                // console.log('Users 1:: ' + JSON.stringify(response.data))
                // console.log('Users 2:: ' + JSON.stringify(response.data.data))
                this.users = response.data;
            }
            )
            .catch(error => console.log('User Get Error:: ' + error))
        },
        createUser: async function() {
            const requestData = {
                firstName: this.userCreation.firstName,
                lastName: this.userCreation.lastName,
            };
            await api.createUser(requestData);
            this.userCreation.firstName = "";
            this.userCreation.lastName = "";
            this.readUsers();
            this.responseSuccess = true;
        },
    },
    mounted() {
        //this.readUsers();
        this.getData();
    },
    created() {
        //this.readUsers();
        this.getData();
    },
};
</script>