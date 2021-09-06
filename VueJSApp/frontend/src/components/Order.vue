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
        <v-select
          v-model="orderCreation.product_id"
          :items="products"
          label="Choose Product"
          filled
          outlined
          dense
          no-data-text="There are no products"
          value=0
          @change="onChangeProduct"
        ></v-select>
        <v-text-field
          v-model="orderCreation.quantity"
          label="Quantity"
        ></v-text-field>
        <v-btn color="blue" v-on:click="saveOrder">
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
    name: "Order",
    data: vm => ({
        orderCreation: 
        {
            product_id: 0,
            quantity: "",
        },
        orders: [],
        products: [],
        responseSuccess: false,
        snackbar: false,
        displayMessage: "",
        color: 'general',
        headers: [
            { text: 'ID', value: 'id' },
            { text: 'Quantity', value: 'quantity' },
            { text: 'Fulfilled', value: 'is_fullfilled' },
            { text: 'Date Ordered', value: 'date_ordered' },
            { text: 'Date Fulfilled', value: 'date_fullfilled' },
            { text: 'Product Name', value: 'product.name' },
            { text: 'Actions', value: 'actions', sortable: false },
        ],
    }),
    methods: {
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
        getProducts() {
            this.$http.get('/product', {
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
                //this.products = response.data;
                // this.product_names = this.decodeTypeNames(response.data);
                // this.product_values = this.decodeTypeIds(response.data);
                // this.products = this.getProductIds();
                this.products = this.getProductIds(this.decodeProductNames(response.data), this.decodeProductIds(response.data));
            }
            )
            .catch(error => console.log('Products Get Error:: ' + error))
        },
        getProductIds(product_names, product_ids)
        {
            let tmp = [];
            // let product_values = this.product_values;
            // let product_names = this.product_names;
            for(let i = 0; i < product_ids.length; i++)
            {
                tmp.push({'value': product_ids[i], 'text': product_names[i]});
            }
            // console.log('Got Final Product IDs As:: ' + JSON.stringify(tmp));
            return(tmp);
        },
        decodeProductNames(data) {
            let types = data.map(p => p.name);
            // console.log('Got Product Names As:: ' + JSON.stringify(types));
            return(types);
        },
        decodeProductIds(data) {
            let ids = data.map(p => p.id);
            // console.log('Got Product IDs As:: ' + JSON.stringify(ids));
            return(ids);
        },
        onChangeProduct()
        {
            console.log('Got Selected Product As:: ' + JSON.stringify(this.orderCreation.product_id));
        },
        saveOrder() {
            const requestData = {
                product_id: this.orderCreation.product_id,
                quantity: this.orderCreation.quantity,
            };
            this.$http.post('/order', requestData, {
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
                    //this.orders = response.data;
                    this.displayMessage = "Order Saved Successfully";
                    this.snackbar = true;
                    console.log('Order saved successfully');
                }
            )
            .catch(error => {
                console.log('Order Save Error:: ' + error)
                this.displayMessage = "Error: Failed to Save Order";
                this.snackbar = true;
            })
        },
    },
    mounted() {
        this.getData();
        this.getProducts();
    },
    created() {
        this.getData();
        this.getProducts();
    },
    watch: {
        orderCreation: {
            handler () {
                console.log('Got Selected Product As:: ' + JSON.stringify(this.orderCreation.product_id))
            }
        },
    },
};
</script>