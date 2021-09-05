<template>
  <v-app id="inspire">
    <v-app-bar app color="white" flat>
      <v-container class="py-0 fill-height">
        
        <!-- Menu Buttons -->
        <v-btn to="/order" text>Orders</v-btn>
        <v-btn to="/product" text>Products</v-btn>
        <v-btn to="/sale" text>Sales</v-btn>
        <v-btn to="/user" text>Users</v-btn>
        <v-btn @click.prevent="logout" text>Logout</v-btn>
        <v-spacer></v-spacer>
        <v-responsive max-width="260"> </v-responsive>
      </v-container>
    </v-app-bar>
    <!-- Main content -->
    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col>
            <v-sheet min-height="70vh" rounded="lg">
              <!--  -->
              <router-view></router-view>
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "Dashboard",
  data() {
    return {
    };
  },
  methods: {
    logout: function () {
      this.$store.dispatch('logout').then(() => {
        this.$router.push('/')
      })
    },
  },
  mounted() {
  },
};
</script>