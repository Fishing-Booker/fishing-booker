import axios from "axios";

const baseUrl = "http://localhost:8080/"

export default {
    users(url = baseUrl + 'users/') {
        return {
            getAll: () => axios.get(url),
            getById: id => axios.get(url + id),
            addUser: newUser => axios.post(url + "add", newUser),
            updateUser: (id, updatedUser) => axios.put(url + id, updatedUser),
            deleteUser: id => axios.delete(url + id)
        }
    }
}