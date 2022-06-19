import React from 'react';
import UserService from '../../services/user.service';
import {User} from '../../models/user';
import {Transaction} from '../../models/transaction';

class HomePage extends React.Component{

  constructor(props){
    super(props);

    this.state = {
      bikes: [],
      errorMessage: '',
      infoMessage: '',
      currentUser: new User(),
    };
  }

  componentDidMount() {
    UserService.currentUser.subscribe(data =>{
      this.setState({
        currentUser: data
      })
    });

    this.setState({
      bikes: {loading: true}
    });

    UserService.findAllBikes().
      then(bikes => {
        this.setState({bikes: bikes.data});
      });
  }

  rentBike(bike) {
    if(!this.state.currentUser){
      this.setState({errorMessage: "You should sign in to rent a bike"});
      return;
    }

    var transaction = new Transaction(this.state.currentUser, bike);
    UserService.rentBike(transaction)
      .then(data => {
        this.setState({infoMessage: "Mission is completed."});
      },error => {
        this.setState({errorMessage: "Unexpected error occurred."});
      });
  }
  returnBike(bike) {
    if(!this.state.currentUser){
      this.setState({errorMessage: "You should sign in to return a bike"});
      return;
    }

    var transaction = new Transaction(this.state.currentUser, bike);
    UserService.returnBike(transaction)
        .then(data => {
          this.setState({infoMessage: "Mission is completed."});
        },error => {
          this.setState({errorMessage: "Unexpected error occurred."});
        });
  }
  detail(bike) {
    localStorage.setItem('currentBike', JSON.stringify(bike));
    this.props.history.push('/detail/'+bike.id);
  }

  render() {
    const {bikes, infoMessage, errorMessage} = this.state;
    return (
      <div className="col-md-12">
      {infoMessage &&
        <div className="alert alert-success">
          <strong>Successfully! </strong> {infoMessage}
          <button type="button" className="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
      }
      {errorMessage &&
        <div className="alert alert-danger">
          <strong>Error! </strong> {errorMessage}
          <button type="button" className="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
      }
      {bikes.loading && <em> Loading bikes...</em>}
      {bikes.length &&
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Name</th>
              <th scope="col">Price</th>
              <th scope="col">Rented?</th>
              <th scope="col">Detail</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {bikes.map((bike, index) =>
                <tr key={bike.id}>
                  <th scope="row">{index + 1}</th>
                  <td>{bike.name}</td>
                  <td>{'$ ' + bike.price}</td>
                  <td>{'No ' }</td>
                  <td>
                    <button className="btn btn-info" onClick={() => this.detail(bike)}>Detail</button>
                  </td>
                  <td>
                    <button className="btn btn-success" onClick={() => this.rentBike(bike)}>Rent bike</button>
                  </td>
                  <td>
                    <button className="btn btn-success" onClick={() => this.returnBike(bike)}>Return</button>
                  </td>
                </tr>
            )
            }
          </tbody>
        </table>
      }
      </div>
    );
  }

}

export {HomePage};
