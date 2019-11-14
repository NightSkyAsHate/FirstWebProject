<template>
    <v-text-field class="text--primary" outlined readonly :label="this.label" :value="this.value">
    </v-text-field>
</template>

<script>
    export default {
        name: "cargoElement",
        props: ['value', 'label']
    }
</script>

<style scoped>

</style>