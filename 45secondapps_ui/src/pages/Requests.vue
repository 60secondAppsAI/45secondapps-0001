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
            <request-table
            v-if="requests && requests.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:requests="requests"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-requests="getAllRequests"
             >

            </request-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RequestTable from "@/components/RequestTable";
import RequestService from "../services/RequestService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RequestTable,
  },
  data() {
    return {
      requests: [],
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
    async getAllRequests(sortBy='requestId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RequestService.getAllRequests(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.requests.length) {
					this.requests = response.data.requests;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching requests:", error);
        }
        
      } catch (error) {
        console.error("Error fetching request details:", error);
      }
    },
  },
  mounted() {
    this.getAllRequests();
  },
  created() {
    this.$root.$on('searchQueryForRequestsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRequests();
    })
  }
};
</script>
<style></style>
