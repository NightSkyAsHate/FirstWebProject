<template>
    <v-text-field class="text--primary" outlined readonly label="Owner" :value="this.user.username"></v-text-field>
</template>

<script>
    export default {
        name: "userForm",
        props: ['user']
    }
</script>

<style scoped>

</style>