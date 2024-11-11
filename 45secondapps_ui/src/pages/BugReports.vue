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
            <bugReport-table
            v-if="bugReports && bugReports.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:bugReports="bugReports"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-bug-reports="getAllBugReports"
             >

            </bugReport-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BugReportTable from "@/components/BugReportTable";
import BugReportService from "../services/BugReportService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BugReportTable,
  },
  data() {
    return {
      bugReports: [],
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
    async getAllBugReports(sortBy='bugReportId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BugReportService.getAllBugReports(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.bugReports.length) {
					this.bugReports = response.data.bugReports;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching bugReports:", error);
        }
        
      } catch (error) {
        console.error("Error fetching bugReport details:", error);
      }
    },
  },
  mounted() {
    this.getAllBugReports();
  },
  created() {
    this.$root.$on('searchQueryForBugReportsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBugReports();
    })
  }
};
</script>
<style></style>
