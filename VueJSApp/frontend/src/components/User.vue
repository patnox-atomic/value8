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
        <v-btn color="blue" v-on:click="saveUser">
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
    <v-snackbar
        v-model="snackbar"
        :color="color"
        :top="true"
    >
        {{ displayMessage }}
        <v-btn
            dark
            text
            @click="snackbar = false"
        >
            Close
        </v-btn>
    </v-snackbar>
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
        snackbar: false,
        displayMessage: "",
        color: 'general',
    }),
    methods: {
        getData() {
            this.$http.get('/api/v1/user', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            })
            .then(response => {
                // console.log('Users 1:: ' + JSON.stringify(response.data))
                // console.log('Users 2:: ' + JSON.stringify(response.data.data))
                this.users = response.data;
            }
            )
            .catch(error => console.log('User Get Error:: ' + error))
        },
        saveUser() {
            const requestData = {
                firstName: this.userCreation.firstname,
                lastName: this.userCreation.lastname,
                email: this.userCreation.email,
                password: this.userCreation.password,
            };
            this.$http.post('/api/v1/user', requestData, {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token')
                    },
                })
                .then(response => {
                    // console.log('Orders 1:: ' + JSON.stringify(response.data))
                    // console.log('Orders 2:: ' + JSON.stringify(response.data.data))
                    this.getData();
                    this.displayMessage = "User Saved Successfully";
                    this.snackbar = true;
                    console.log('User saved successfully');
                }
            )
            .catch(error => {
                console.log('User Save Error:: ' + error)
                this.displayMessage = "Error: Failed to Save User";
                this.snackbar = true;
            })
        },
    },
    mounted() {
        this.getData();
    },
    created() {
        this.getData();
    },
};
</script>