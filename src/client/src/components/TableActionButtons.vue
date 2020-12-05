<template>
  <div id="action-buttons">
    <div class="action-button-container">
      <router-link
        id="pm-zero"
        :to="`/admin/detail/${resource}/${id}`"
      >
        <i class="far fa-eye has-text-dark icon-center"></i>
      </router-link>
    </div>
    <div class="action-button-container">
      <router-link
        id="pm-zero"
        :to="`/admin/edit/${resource}/${id}`"
      >
        <i class="far fa-edit has-text-dark icon-center"></i>
      </router-link>
    </div>
    <div class="action-button-container">
      <router-link
        id="pm-zero"
        :to="`/admin/delete/${resource}/${id}`"
      >
        <i class="far fa-trash-alt has-text-dark icon-center"></i>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
    props: ["resource", "id"],
};
</script>

<style lang="scss" scoped>
#action-buttons .action-button-container {
    padding: 0.3em;
    display: inline-block;
    border-radius: 3px;
    width: 2.5em;
    margin: 0.1em;
}
#action-buttons .action-button-container:nth-child(1) {
    background-color: #5199e4;
}
#action-buttons .action-button-container:nth-child(2) {
    background-color: #ffdd57;
}
#action-buttons .action-button-container:nth-child(3) {
    background-color: #e65b65;
}
#action-buttons .action-button-container:nth-child(1):hover {
    background-color: hsl(211, 73%, 51%);
}
#action-buttons .action-button-container:nth-child(2):hover {
    background-color: hsl(48, 100%, 57%);
}
#action-buttons .action-button-container:nth-child(3):hover {
    background-color: hsl(356, 74%, 53%);
}
</style>
