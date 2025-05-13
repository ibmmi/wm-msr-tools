# WebMethods Microservices Runtime Tools

This repository contains a collection of custom tools to be used with or for MSR.
While the fundamental intention is to use them with MSR, they are working with Integration Server in general under certain conditions.

## Rules

With the transition from Software AG to IBM and some lessons learned, the repository now follows these rules:

- "Wx" packages actually have a different strategy. The contents of these tools are gradually moved and will reside in "Wz" packages. The move is a refactor and consolidation in a single place for these tools. Other work streams will continue to work with "Wx" packages, this repository represents a different approach
- Packages have now different "by design" intended use:
  - WzD - Design or Development time
  - WzT - Test or build time, i.e. intended to be run exclusively at CI time, in unattended and ephemeral CI agents
  - WzP - Production time. Utilities that are useful at runtime, albeit for configuration management or production testing like diagnostics
  - WzD may depend on the others, WzT may only depend on WzP
- Features are added as needed during execution of real world projects
- The repository MUST not contain any form of compiled artifact, thus no `.class`, `.frag`, `.jar`, `.zip` and similar files. These are either built or recovered ad build time from source, like for example from the public maven repository
  - Downloading binaries MUST guarantee the file integrity with cryptographic hashes like sha256
- Each package has a corresponding "${Name}LocalTest" package that is used for testing purposes and not intended for final production deployment.

## Remember

This repo does not contain the compiled form of the code. Besides, the compiled code depends on the MSR version used. Compile the code while the runtimes are down by going to `run-configurations/jcode-build` and running `run.bat`.
Also, use an image for development and compilation that has the compiler included. For example, the slim images do not have the compiler included. With these you can develop flow code, but not java code.
