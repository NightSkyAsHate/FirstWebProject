<template>
    <v-text-field class="text--primary" outlined readonly :label="this.labelText" :value="this.weight.name"></v-text-field>
</template>

<script>
    export default {
        name: "weightForm",
        props: ['weight', 'labelText']
    }
</script>

<style scoped>

</style>