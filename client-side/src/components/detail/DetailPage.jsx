import React from 'react';

class DetailPage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      bike: JSON.parse(localStorage.getItem('currentBike'))
    };
  }

  render() {
    return (
      <div className="jumbotron">
        <h1 className="display-4">Bike: {this.state.bike.name}</h1>
        <h1 className="display-4">Bike id: {this.state.id}</h1>
      </div>
    );
  }

}

export {DetailPage};
