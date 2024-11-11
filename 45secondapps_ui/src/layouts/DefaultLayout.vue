<template>
  <a-layout style="min-height: 100vh">
    <!-- Header -->
    <a-layout-header style="background: #fff; padding: 0">
      <app-header />
    </a-layout-header>

    <!-- Main layout with sider and content -->
    <a-layout>
      <!-- Collapsible Sider with Controlled Open Keys for Nested Menu -->
      <a-layout-sider width="200px" collapsible style="background: #fff">
        <a-menu
          mode="inline"
          :openKeys="openKeys"
          @openChange="onOpenChange"
          style="height: 100%; border-right: 0"
        >
<!--          <a-sub-menu key="sub1" title="Menu 1">
            <a-menu-item key="1">Option 1</a-menu-item>
            <a-menu-item key="2">Option 2</a-menu-item>
            <a-sub-menu key="sub1-1" title="Nested Menu">
              <a-menu-item key="3">Option 3</a-menu-item>
              <a-menu-item key="4">Option 4</a-menu-item>
            </a-sub-menu>
          </a-sub-menu>
          <a-sub-menu key="sub2" title="Menu 2">
            <a-menu-item key="5">Option 5</a-menu-item>
            <a-menu-item key="6">Option 6</a-menu-item>
          </a-sub-menu>
-->>

	 
			
				<a-menu-item>
					<router-link to="/appGenerators">
						<span class="label">App Generators</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/requests">
						<span class="label">Requests</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/users">
						<span class="label">Users</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/templates">
						<span class="label">Templates</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/apps">
						<span class="label">Apps</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/features">
						<span class="label">Features</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/appFeatures">
						<span class="label">App Features</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/accounts">
						<span class="label">Accounts</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/billings">
						<span class="label">Billings</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/subscriptions">
						<span class="label">Subscriptions</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/reviews">
						<span class="label">Reviews</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/categorys">
						<span class="label">Categorys</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/components">
						<span class="label">Components</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/appComponents">
						<span class="label">App Components</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/logs">
						<span class="label">Logs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/developers">
						<span class="label">Developers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/applicationDevelopers">
						<span class="label">Application Developers</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/bugReports">
						<span class="label">Bug Reports</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/fixs">
						<span class="label">Fixs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/supportTickets">
						<span class="label">Support Tickets</span>
					</router-link>
				</a-menu-item>
		
	
        </a-menu>
      </a-layout-sider>
      
      <!-- Content area -->
      <a-layout-content style="padding: 16px">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
import { ref } from "vue";
import AppHeader from "@/components/AppHeader.vue";

export default {
  name: "DefaultLayout",
  components: {
    AppHeader,
  },
  setup() {
    const openKeys = ref(["sub1"]); // default open submenu
    const onOpenChange = (keys) => {
      // Open only the last clicked submenu, for single open behavior
      openKeys.value = keys.length ? [keys[keys.length - 1]] : [];
    };

    return {
      openKeys,
      onOpenChange,
    };
  },
};
</script>
<style>
.ant-layout {
    margin-top: 20px;
}
</style>

