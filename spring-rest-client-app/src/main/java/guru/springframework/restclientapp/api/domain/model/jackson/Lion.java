//: guru.springframework.restclientapp.api.domain.model.jackson.Lion.java


package guru.springframework.restclientapp.api.domain.model.jackson;


public class Lion extends Animal {

	public Lion(String name) {
		super(name);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Lion{");
		sb.append("name: ")
				.append(this.name)
				.append('}');

		return sb.toString();
	}
}///~