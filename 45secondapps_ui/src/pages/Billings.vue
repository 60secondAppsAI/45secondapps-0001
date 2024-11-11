<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <billing-table
            v-if="billings && billings.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:billings="billings"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-billings="getAllBillings"
             >

            </billing-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BillingTable from "@/components/BillingTable";
import BillingService from "../services/BillingService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BillingTable,
  },
  data() {
    return {
      billings: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllBillings(sortBy='billingId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BillingService.getAllBillings(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.billings.length) {
					this.billings = response.data.billings;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching billings:", error);
        }
        
      } catch (error) {
        console.error("Error fetching billing details:", error);
      }
    },
  },
  mounted() {
    this.getAllBillings();
  },
  created() {
    this.$root.$on('searchQueryForBillingsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBillings();
    })
  }
};
</script>
<style></style>
