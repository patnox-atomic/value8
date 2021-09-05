<template>
  <v-container>
    <h1>Order Management</h1>
    <p>Orders</p>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          You have successfully added an order
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>Order Creation</h3>
        <v-text-field
          v-model="orderCreation.product"
          label="Product"
        ></v-text-field>
        <v-text-field
          v-model="orderCreation.quantity"
          label="Quantity"
        ></v-text-field>
        <v-btn color="blue" v-on:click="createOrder">
          Create Order
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Orders</h3>
        <v-simple-table>
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">
                  ID
                </th>
                <th class="text-left">
                  Quantity
                </th>
                <th class="text-left">
                  FullFilled
                </th>
                <th class="text-left">
                  Date Ordered
                </th>
                <th class="text-left">
                  Date FullFilled
                </th>
                <th class="text-left">
                  Product
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="order in orders" :key="order.id">
                <td>{{ order.id }}</td>
                <td>{{ order.quantity }}</td>
                <td>{{ order.is_fullfilled }}</td>
                <td>{{ order.date_ordered }}</td>
                <td>{{ order.date_fullfilled }}</td>
                <td>{{ order.product.name }}</td>
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
    name: "Order",
    data: vm => ({
        orderCreation: 
        {
            product: "",
            quantity: "",
        },
        orders: [],
        responseSuccess: false,
    }),
    methods: {
        readOrders: async function() {
            const data = await api.readOrders();
            this.orders = data;
            //console.log('Orders 1:: ' + JSON.stringify(this.orders));
        },
        getData() {
            this.$http.get('/order', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            })
            .then(response => {
                ////this.total_items = Number(response.data.meta.querytotal);
                ////this.payload = response.data.data;
                ////this.loading = false;
                // console.log('Orders 1:: ' + JSON.stringify(response.data))
                // console.log('Orders 2:: ' + JSON.stringify(response.data.data))
                this.orders = response.data;
            }
            )
            .catch(error => console.log('Order Get Error:: ' + error))
        },
        createOrder: async function() {
            const requestData = {
                firstName: this.orderCreation.firstName,
                lastName: this.orderCreation.lastName,
            };
            await api.createOrder(requestData);
            this.orderCreation.firstName = "";
            this.orderCreation.lastName = "";
            this.readOrders();
            this.responseSuccess = true;
        },
    },
    mounted() {
        //this.readOrders();
        this.getData();
    },
    created() {
        //this.readOrders();
        this.getData();
    },
};
</script>