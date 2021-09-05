<template>
  <v-container>
    <h1>Sale Management</h1>
    <p>Sales</p>
    <v-row>
      <v-col sm="12">
        <v-alert v-if="responseSuccess" dense text type="success">
          You have successfully added a sale
        </v-alert>
      </v-col>
      <v-col sm="6">
        <h3>Sale Creation</h3>
        <v-text-field
          v-model="saleCreation.product"
          label="Product"
        ></v-text-field>
        <v-text-field
          v-model="saleCreation.quantity"
          label="Quantity"
        ></v-text-field>
        <v-text-field
          v-model="saleCreation.price"
          label="Price"
        ></v-text-field>
        <v-btn color="blue" v-on:click="createSale">
          Create Sale
        </v-btn>
      </v-col>
      <v-col sm="6">
        <h3>Sales</h3>
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
                  Price
                </th>
                <th class="text-left">
                  Sale Date
                </th>
                <th class="text-left">
                  Product
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="sale in sales" :key="sale.id">
                <td>{{ sale.id }}</td>
                <td>{{ sale.quantity }}</td>
                <td>{{ sale.price }}</td>
                <td>{{ sale.sale_date }}</td>
                <td>{{ sale.product.name }}</td>
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
    name: "Sale",
    data: vm => ({
        saleCreation: 
        {
            product: "",
            quantity: "",
            price: ""
        },
        sales: [],
        responseSuccess: false,
    }),
    methods: {
        readSales: async function() {
            const data = await api.readSales();
            this.sales = data;
            //console.log('Sales 1:: ' + JSON.stringify(this.sales));
        },
        getData() {
            this.$http.get('/sale', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token')
            },
            })
            .then(response => {
                ////this.total_items = Number(response.data.meta.querytotal);
                ////this.payload = response.data.data;
                ////this.loading = false;
                // console.log('Sales 1:: ' + JSON.stringify(response.data))
                // console.log('Sales 2:: ' + JSON.stringify(response.data.data))
                this.sales = response.data;
            }
            )
            .catch(error => console.log('Sale Get Error:: ' + error))
        },
        createSale: async function() {
            const requestData = {
                firstName: this.saleCreation.firstName,
                lastName: this.saleCreation.lastName,
            };
            await api.createSale(requestData);
            this.saleCreation.firstName = "";
            this.saleCreation.lastName = "";
            this.readSales();
            this.responseSuccess = true;
        },
    },
    mounted() {
        //this.readSales();
        this.getData();
    },
    created() {
        //this.readSales();
        this.getData();
    },
};
</script>